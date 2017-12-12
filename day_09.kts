import java.io.File
import java.util.*

data class Result(val score: Int, val garbage: Int)

fun process(input: String): Result {
    var score = 0
    var totalScore = 0
    var garbage = false
    var garbageCounter = 0

    var i = 0
    while (i in input.indices) {
        when (input[i]) {
            '!' -> i++
            '<' -> if (!garbage) garbage = true
                   else garbageCounter += 1
            '>' -> garbage = false
            '{' -> if (!garbage) score++
                   else garbageCounter += 1
            '}' -> if (!garbage) totalScore += score--
                   else garbageCounter += 1
            else -> if (garbage) garbageCounter += 1
        }
        i++
    }
    return Result(totalScore, garbageCounter)
}

val input = File("input/day_09").readText()

println("solution 1: " + process(input).score)
println("solution 2: " + process(input).garbage)

require(process("{}").score == 1)
require(process("{{{}}}").score == 6)
require(process("{{},{}}").score == 5)
require(process("{{{},{},{{}}}}").score == 16)
require(process("{<a>,<a>,<a>,<a>}").score == 1)
require(process("{{<ab>},{<ab>},{<ab>},{<ab>}}").score == 9)
require(process("{{<!!>},{<!!>},{<!!>},{<!!>}}").score == 9)
require(process("{{<a!>},{<a!>},{<a!>},{<ab>}}").score == 3)

require(process("<>").garbage == 0)
require(process("<random characters>").garbage == 17)
require(process("<<<<>").garbage == 3)
require(process("<{!>}>").garbage == 2)
require(process("<!!").garbage == 0)
require(process("<!!!>>").garbage == 0)
require(process("<{o\"i!a,<{i<a>").garbage == 10)

