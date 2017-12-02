import java.io.File

fun captcha(input: String, step: Int) =
        input.indices.filter { input[it] == input[(it+step) % input.length] }
                     .sumBy { input[it]-'0' }

val digits = File("day_01_input").readText()
println("solution 1: " + captcha(digits, 1))
println("solution 2: " + captcha(digits, digits.length/2))