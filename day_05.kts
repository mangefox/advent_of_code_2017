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

fun get_input() = File("day_05_input").readLines().map { it.toInt() }.toMutableList()

require(trampolines1(get_input()) == 364539)
println("solution 1: ${trampolines1(get_input())}")

require(trampolines2(get_input()) == 27477714)
println("solution 2: ${trampolines2(get_input())}")
