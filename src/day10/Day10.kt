package day10

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val openingChars = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val scores = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        return input.mapNotNull { row ->
            val stack = ArrayDeque<Char>()

            row.forEach { char ->
                when {
                    char in openingChars -> stack.addLast(char) // if it is an opening character
                    openingChars[stack.removeLast()] != char -> return@mapNotNull char
                }
            }

            return@mapNotNull null
        }
            .mapNotNull { scores[it] }
            .sumOf { it }
    }

    fun part2(input: Array<IntArray>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val day = "10"
    val testInput = readInput("day$day/Day${day}_test")

    check(part1(testInput) == 26397)
//    check(part2(testInput) == 1134)

    val input = readInput("day$day/Day$day")
    println(part1(input))
//    println(part2(input))
}

fun findOpposite(s: String): String {
    return when (s) {
        "(" -> ")"
        "[" -> "]"
        "{" -> "}"
        "<" -> ">"
        else -> throw IllegalStateException("invalid input $s")
    }
}
