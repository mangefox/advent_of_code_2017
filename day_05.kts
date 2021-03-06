import java.io.File

fun trampolines1(input: MutableList<Int>): Int {
    var pos = 0
    var steps = 0
    while (pos in input.indices) {
        pos += input[pos]++
        steps++
    }
    return steps
}

fun trampolines2(input: MutableList<Int>): Int {
    var pos = 0
    var steps = 0
    while (pos in input.indices) {
        pos += if (input[pos] >=3) input[pos]-- else input[pos]++
        steps++
    }
    return steps
}

fun getInput() = File("input/day_05").readLines().map { it.toInt() }.toMutableList()
println("solution 1: " + trampolines1(getInput()))
println("solution 2: " + trampolines2(getInput()))
