import java.io.File
import kotlin.math.*

fun spiral1(input: Int): Int {
    val sqrt = ceil(sqrt(input.toDouble())).toInt() // find root of closest square higher than input
    val corner = sqrt*sqrt // value of the "bottom right" corner
    val corners = (0..3).map { corner-(sqrt-1)*it } // get values of all four corners
    val distToCorner = corners.map { abs(it-input) }.min() // find distance to closest corner
    return ((sqrt-1)-distToCorner!!) // corners are sqrt-1 distance from center
}

fun spiral2(input: Int): Int {
    val mid = 20
    val matrix = Array(mid*2) { IntArray(mid*2) }
    matrix[mid][mid] = 1

    fun adjacentSum(row: Int, col: Int) =
            (-1..1).sumBy { i -> (-1..1).sumBy { j -> matrix[row+i][col+j] } }

    var (x, y, dir, dist) = listOf(0, 0, 1, 1)
    while (true) {
        while (y * dir * 2 < dist) {
            val sum = adjacentSum(mid-x, mid+y)
            if (sum > input) return sum else matrix[mid-x][mid+y] = sum
            y += dir
        }
        while (x * dir * 2 < dist) {
            val sum = adjacentSum(mid-x, mid+y)
            if (sum > input) return sum else matrix[mid-x][mid+y] = sum
            x += dir
        }
        dir *= -1
        dist += 1
    }
}

val input = 325489
println("solution 1: " + spiral1(input))
println("solution 2: " + spiral2(input))
