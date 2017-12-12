import java.io.File
import java.util.*

fun List<Int>.toDenseHash() =
        this.chunked(size = 16) { it.reduce { x, y -> x xor y} }
            .joinToString("") { String.format("%02x", it) }

fun reverseByIndices(listToModify: MutableList<Int>, indices: List<Int>) {
    if (indices.size < 2) return
    var head = 0
    var tail = indices.lastIndex
    do {
        Collections.swap(listToModify, indices[head++], indices[tail--])
    } while (head < tail)
}

fun twist(input: List<Int>, rounds: Int = 1): List<Int> {
    val rope = (0 until 256).toMutableList()
    var skip = 0
    var pos = 0
    repeat(rounds) {
        for (i in input) {
            val indices = (pos until pos+i).map { it % rope.size }
            reverseByIndices(rope, indices)
            pos = (pos + i + skip) % rope.size
            skip++
        }
    }
    return rope
}

val input1 = File("day_10_input").readText().split(",").map(String::toInt)
println("solution 1: " + twist(input1).slice(0..1).reduce(Int::times))

val input2 = File("day_10_input").readText().toCharArray().map(Char::toInt) + listOf(17, 31, 73, 47, 23)
println("solution 2: " + twist(input2, rounds = 64).toDenseHash())
