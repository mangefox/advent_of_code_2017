import java.io.File

fun passphrases1(lines: List<String>) =
        lines.map { it.split(" ") }.count { it.distinct() == it }

fun passphrases2(lines: List<String>) =
        lines.map { it.split(" ").map { it.toList().sorted().toString() } }.count { it.distinct() == it }

val lines = File("day_04_input").readLines()
println("solution 1: ${passphrases1(lines)}")
println("solution 2: ${passphrases2(lines)}")