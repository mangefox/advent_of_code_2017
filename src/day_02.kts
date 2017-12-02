import java.io.File

fun checksum1(input: List<List<Int>>) = input.sumBy { it.max()!! - it.min()!! }

fun checksum2(input: List<List<Int>>) =
        input.map { it.sortedDescending() }
             .sumBy { row ->
                 row.sumBy { j ->
                     row.filter { j != it && j % it == 0 }
                        .sumBy { j / it }
                 }
             }

val input = File("day_02_input").readLines().map { it.split("\t").map { it.toInt() } }
println("solution 1: " + checksum1(input))
println("solution 2: " + checksum2(input))
