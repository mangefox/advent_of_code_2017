import java.io.File

val input = File("input/day_06").readText().split("\t").map { it.toInt() }

val seen = mutableListOf<List<Int>>()
val banks = input.toMutableList()
var cycles = 0

while (banks !in seen) {
    val max = banks.withIndex().maxBy { it.value }!!
    seen.add(banks.toList())
    banks[max.index] = 0
    for (i in max.index + 1..max.index + max.value) {
        banks[i % banks.size] += 1
    }
    cycles++
}

println("solution 1: $cycles")
println("solution 2: ${cycles - seen.indexOf(banks)}")