import java.io.File

fun reverseByIndices(listToModify: MutableList<Int>, indices: List<Int>) {
    if (indices.size < 2) return
    var head = 0
    var tail = indices.lastIndex
    do {
        val tmp = listToModify[indices[head]]
        listToModify[indices[head++]] = listToModify[indices[tail]]
        listToModify[indices[tail--]] = tmp
    } while (head < tail)
}

fun twist(input: List<Int>, rounds: Int = 1): MutableList<Int> {
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

fun reduceToDenseHash(rope: MutableList<Int>) =
        rope.chunked(size = 16)
            .map { it.reduce { x, y -> x xor y} }
            .joinToString("") { Integer.toString(it, 16)
                                       .padStart(2, '0') }

val input = File("day_10_input").readText().split(",").map(String::toInt)
val part1 = twist(input)
println("solution 1: " + part1[0] * part1[1])

val input2 = File("day_10_input").readText().toCharArray().map(Char::toInt) + listOf(17, 31, 73, 47, 23)
println("solution 2: " + reduceToDenseHash(twist(input2, rounds = 64)))
