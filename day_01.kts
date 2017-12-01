import java.io.File

// problem 1
fun sumSequence1(digits: List<Int>) =
        (digits + digits.first())
                .zipWithNext { x, y -> if (x == y) x else 0 }
                .sum()

// problem 2
fun sumSequence2(digits: List<Int>) =
        (digits + digits.take(digits.size / 2))
                .windowed(1 + digits.size / 2)
                .map { if (it.first() == it.last()) it.first() else 0 }
                .sum()

val digits = File("day_01_input").readText().map { it.toInt()-48 }
println("solution 1: ${sumSequence1(digits)}") // 1393
println("solution 2: ${sumSequence2(digits)}") // 1292