import java.time.*
import java.time.chrono.ChronoPeriod
import java.time.temporal.ChronoUnit
import java.util.*

private class ShiftTime {
    var startTime: LocalDateTime = LocalDateTime.now()
    var endTime: LocalDateTime? = null

    val isOpen: Boolean
        get() = endTime == null

    val isClosed: Boolean
        get() = endTime != null
}

private class LaborData(
    val map: HashMap<Int, Stack<ShiftTime>> = HashMap<Int, Stack<ShiftTime>>()
) {

    fun getAllEmployeeIds(): Set<Int> {
        return map.keys
    }

    fun getAllShiftsByEmployee(employeeId: Int): Stack<ShiftTime> {
        return map[employeeId] ?: Stack<ShiftTime>()
    }

    fun getLastShiftByEmployee(employeeId: Int): ShiftTime? {
        val shiftTimes = getAllShiftsByEmployee(employeeId)
        return if (shiftTimes.isEmpty()) {
            null
        } else {
            shiftTimes.peek()
        }
    }

    fun isEmployeeClockedIn(employeeId: Int): Boolean {
        return getLastShiftByEmployee(employeeId)?.isOpen ?: false
    }

    fun pushNewShift(employeeId: Int) {
        if (map[employeeId] == null) {
            map[employeeId] = Stack<ShiftTime>()
        }
        map[employeeId]!!.add(ShiftTime())
    }
}

private class LaborService(
    val data: LaborData = LaborData()
) {
    fun clockIn(employeeId: Int) {
        val lastShift = data.getLastShiftByEmployee(employeeId)

        if (lastShift == null) {
            data.pushNewShift(employeeId)
            return
        }

        // Check if last time shift is still open
        if (lastShift.isOpen) {
            throw UnsupportedOperationException("Cannot clock-in again without clock-out the previous one")
        }

        data.pushNewShift(employeeId)
    }

    fun clockOut(employeeId: Int) {
        val lastShift = data.getLastShiftByEmployee(employeeId)
            ?: throw UnsupportedOperationException("Cannot clock-out as this employee is not registered")

        if (lastShift.isClosed) {
            throw UnsupportedOperationException("Cannot clock-out as this employee does not have an open clock-in")
        }

        lastShift.endTime = LocalDateTime.now()
    }

    fun getListOfCurrentlyClockedInEmployees(): Array<Int> {
        return data
            .getAllEmployeeIds()
            .filter { data.isEmployeeClockedIn(it) }
            .toTypedArray()
    }

    fun calculateLastWeekWorkedHoursByEmployee(employeeId: Int): Float {
        var workedHours = 0f

        val shifts = data.getAllShiftsByEmployee(employeeId)
        if (shifts.isEmpty()) {
            return workedHours
        }

        val filterEndTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIDNIGHT)
        val filterStartTime = filterEndTime.minusDays(7)

        for (shift in shifts) {
            val shiftStartTime = shift.startTime
            val shiftEndTime = shift.endTime ?: LocalDateTime.MAX

            if (shiftEndTime.isBefore(filterStartTime)) continue
            if (shiftStartTime.isAfter(filterEndTime)) continue

            val from = max(shiftStartTime, filterStartTime)
            val to = min(shiftEndTime, filterEndTime)

            val diffMinutes = from.until(to, ChronoUnit.MINUTES)
            workedHours += (diffMinutes/60f)
        }

        return workedHours
    }

    private fun min(first: LocalDateTime, second: LocalDateTime): LocalDateTime {
        return if (first.isBefore(second)) {
            first
        } else {
            second
        }
    }

    private fun max(first: LocalDateTime, second: LocalDateTime): LocalDateTime {
        return if (first.isAfter(second)) {
            first
        } else {
            second
        }
    }
}

private fun printShifts(service: LaborService, title: String, action: () -> Unit = {}) {
    println("# $title")
    action()
    for (item in service.data.map.entries) {
        println("Employee #${item.key}")
        println("* IsClockedIn=${service.data.isEmployeeClockedIn(item.key)}")
        for (shift in item.value) {
            println("* ClockInAt=${shift.startTime} - ClockOutAt=${shift.endTime}")
        }
    }
    println("ClockedIn=[${service.getListOfCurrentlyClockedInEmployees().joinToString(", ")}]")
    println()
    println()
}

private fun printWorkedHours(service: LaborService, title: String, vararg employeeIds: Int, setup: () -> Unit = {}) {
    println("# $title")
    setup()
    for (employeeId in employeeIds) {
        val workedHours = service.calculateLastWeekWorkedHoursByEmployee(employeeId)
        println("Employee #$employeeId => $workedHours")
    }
    println()
}

fun main() {
    val service = LaborService()

    printShifts(service, "Get empty list of employees")
    printShifts(service, "Clock-in employee 1") { service.clockIn(1) }
    printShifts(service, "Clock-in employee 1 again") {
        try {
            service.clockIn(1)
        } catch (error: Exception) {
            println("Exception thrown: $error")
        }
    }
    printShifts(service, "Clock-in employee 2") { service.clockIn(2) }
    printShifts(service, "Clock-out employee 1") { service.clockOut(1) }
    printShifts(service, "Clock-out employee 1 again") {
        try {
            service.clockOut(1)
        } catch (error: Exception) {
            println("Exception thrown: $error")
        }
    }
    printShifts(service, "Clock-out employee 2") { service.clockOut(2) }

    printWorkedHours(service, "Hours of not registered employee", 1) {
        service.data.map.clear()
    }
    printWorkedHours(service, "Worked 1 hour yesterday", 1) {
        val stack = Stack<ShiftTime>()
        stack.push(ShiftTime().apply {
            startTime = LocalDateTime.now().minusDays(1)
            endTime = LocalDateTime.now().minusDays(1).plusHours(1)
        })
        service.data.map.clear()
        service.data.map[1] = stack
    }
    printWorkedHours(service, "Worked 3.5 hours in 3 days = 10.5", 1) {
        val stack = Stack<ShiftTime>()
        stack.push(ShiftTime().apply {
            startTime = LocalDateTime.now().minusDays(3).minusMinutes(30)
            endTime = LocalDateTime.now().minusDays(3).plusHours(3)
        })
        stack.push(ShiftTime().apply {
            startTime = LocalDateTime.now().minusDays(2).minusMinutes(30)
            endTime = LocalDateTime.now().minusDays(2).plusHours(3)
        })
        stack.push(ShiftTime().apply {
            startTime = LocalDateTime.now().minusDays(1).minusMinutes(30)
            endTime = LocalDateTime.now().minusDays(1).plusHours(3)
        })
        service.data.map.clear()
        service.data.map[1] = stack
    }
    printWorkedHours(service, "Worked all month long = 168", 1) {
        val stack = Stack<ShiftTime>()
        stack.push(ShiftTime().apply {
            startTime = LocalDateTime.now().minusMonths(1)
            endTime = LocalDateTime.now()
        })
        service.data.map.clear()
        service.data.map[1] = stack
    }
}
