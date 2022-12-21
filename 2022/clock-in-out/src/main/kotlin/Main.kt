import java.time.*
import java.util.*

private class ShiftTime {
    var startTime = LocalDateTime.now()
    var endTime: LocalDateTime? = null
}

private class LaborData {
    private val map = HashMap<Int, Stack<ShiftTime>>()

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
        val lastShiftTime = getLastShiftByEmployee(employeeId)
        return if (lastShiftTime == null) {
            false
        } else {
            lastShiftTime.endTime == null
        }
    }

    fun pushNewShift(employeeId: Int) {
        if (map[employeeId] == null) {
            map[employeeId] = Stack<ShiftTime>()
        }
        map[employeeId]!!.add(ShiftTime())
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (item in map.entries) {
            builder.appendLine("Employee #${item.key}")
            builder.appendLine("\tIsClockedIn=${isEmployeeClockedIn(item.key)}")

            for (shift in item.value) {
                builder.appendLine("\tClockInAt=${shift.startTime} - ClockOutAt=${shift.endTime}")
            }
        }

        return builder.toString()
    }
}

private class LaborService {
    private val data = LaborData()

    fun clockIn(employeeId: Int) {
        val lastShift = data.getLastShiftByEmployee(employeeId)

        if (lastShift == null) {
            data.pushNewShift(employeeId)
            return
        }

        // Check if last time shift is still open
        if (lastShift.endTime == null) {
            throw UnsupportedOperationException("Cannot clock-in again without clock-out the previous one")
        }

        data.pushNewShift(employeeId)
    }

    fun clockOut(employeeId: Int) {
        val lastShift = data.getLastShiftByEmployee(employeeId)
            ?: throw UnsupportedOperationException("Cannot clock-out as this employee is not registered")

        if (lastShift.endTime != null) {
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

    fun calculateWorkedHours(employeeId: Int) {
    }

    override fun toString(): String {
        return data.toString()
    }
}

fun main() {
    val service = LaborService()
    println("# Get empty list of employees")
    println(service)
    println()

    println("# Clock-in employee 1")
    service.clockIn(1)
    println(service)
    println()

    println("# Clock-in employee 1 again")
    try {
        service.clockIn(1)
    } catch (error: Exception) {
        println("Exception thrown: $error")
    }
    println(service)
    println()

    println("# Clock-in employee 2")
    service.clockIn(2)
    println(service)
    println()

    println("# Clock-out employee 1")
    service.clockOut(1)
    println(service)
    println()

    println("# Clock-out employee 1 again")
    try {
        service.clockOut(1)
    } catch (error: Exception) {
        println("Exception thrown: $error")
    }
    println(service)
    println()

    println("# Clock-out employee 2")
    service.clockOut(2)
    println(service)
    println()
}
