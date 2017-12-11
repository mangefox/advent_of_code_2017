import com.oracle.util.Checksums.update
import java.io.File
import kotlin.math.max
import kotlin.system.exitProcess

val lines = File("day_08_input").readLines()

val reg = mutableMapOf<String, Int>()
var maxVal = Int.MIN_VALUE

for (line in lines) {
    val x = line.split(" ")
    val reg1 = x[0]; val op1 = x[1]; val amt1 = x[2]
    val reg2 = x[4]; val op2 = x[5]; val amt2 = x[6]

    reg.computeIfAbsent(reg1) { 0 }
    reg.computeIfAbsent(reg2) { 0 }

    fun updateReg(keyToUpdate: String, operation: String, value: Int) {
        when (operation) {
            "inc" -> reg.merge(keyToUpdate, value) { x, y -> x + y }
            "dec" -> reg.merge(keyToUpdate, value) { x, y -> x - y }
        }
    }

    fun checkCondition(keyToCheck: String, operation: String, value: Int) {
        when (operation) {
            "<"  -> if (reg[keyToCheck]!! < value)  updateReg(reg1, op1, amt1.toInt())
            ">"  -> if (reg[keyToCheck]!! > value)  updateReg(reg1, op1, amt1.toInt())
            ">=" -> if (reg[keyToCheck]!! >= value) updateReg(reg1, op1, amt1.toInt())
            "==" -> if (reg[keyToCheck]!! == value) updateReg(reg1, op1, amt1.toInt())
            "<=" -> if (reg[keyToCheck]!! <= value) updateReg(reg1, op1, amt1.toInt())
            "!=" -> if (reg[keyToCheck]!! != value) updateReg(reg1, op1, amt1.toInt())
        }
    }

    checkCondition(reg2, op2, amt2.toInt())
    maxVal = max(maxVal, reg.values.max()!!)
}

println("solution 1: " + reg.entries.sortedBy { it.value }.last().value)
println("solution 2: " + maxVal)
