import java.time.*
import java.util.*

private class ShiftTime {
    var startTime: LocalDateTime = LocalDateTime.now()
    var endTime: LocalDateTime? = null
}

private class LaborService(
    private val map: MutableMap<Int, Stack<ShiftTime>> = HashMap<Int, Stack<ShiftTime>>(),
    private val clockedIn: MutableSet<Int> = HashSet<Int>(),
) {
    fun clockIn(employeeId: Int) {
        var shiftTimes = map[employeeId]

        if (shiftTimes == null) {
            shiftTimes = Stack<ShiftTime>()
            map[employeeId] = shiftTimes
        }

        // First time adding a time shift for employee
        if (shiftTimes.isEmpty()) {
            shiftTimes.push(ShiftTime())
            clockedIn.add(employeeId)
            return
        }

        // Check if last time shift has been closed already
        if (shiftTimes.peek().endTime != null) {
            shiftTimes.push(ShiftTime())
            clockedIn.add(employeeId)
            return
        }

        throw UnsupportedOperationException("Cannot clock-in again without clock-out the previous one")
    }

    fun clockOut(employeeId: Int) {
        val shiftTimes = map[employeeId]

        if (shiftTimes.isNullOrEmpty()) {
            throw UnsupportedOperationException("Cannot clock-out as this employee is not registered")
        }

        val lastShiftTime = shiftTimes.peek()
        if (lastShiftTime.endTime != null) {
            throw UnsupportedOperationException("Cannot clock-out as this employee does not have an open clock-in")
        }

        lastShiftTime.endTime = LocalDateTime.now()
        clockedIn.remove(employeeId)
    }

    fun getListOfCurrentlyClockedInEmployees(): Array<Int> {
        return clockedIn.toTypedArray()
    }

    fun calculateWorkedHours(employeeId: Int) {
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (item in map.entries) {
            builder.appendLine("Employee #${item.key}")
            builder.appendLine("\tIsClockedIn=${clockedIn.contains(item.key)}")

            for (shift in item.value) {
                builder.appendLine("\tClockInAt=${shift.startTime} - ClockOutAt=${shift.endTime}")
            }
        }

        return builder.toString()
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
