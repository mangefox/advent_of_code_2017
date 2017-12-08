import java.io.File

val input = File("day_06_input").readText().split("\t").map { it.toInt() }

val seen = mutableListOf<List<Int>>()
val banks = input.toMutableList()
var count = 0

do {
    val max = banks.withIndex().maxBy { it.value }!!
    seen.add(banks.toList())
    banks[max.index] = 0
    for (i in max.index + 1..max.index + max.value) {
        banks[i % banks.size] += 1
    }
    count++
} while (banks !in seen)

println("solution 1: $count")
println("solution 2: ${count - seen.indexOf(banks)}")