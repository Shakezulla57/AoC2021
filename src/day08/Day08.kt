package day08

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val things = setOf(2,4,3,7)
        return input
            .groupingBy { it }
            .eachCount()
            .filterKeys { it.length in things }
            .values
            .sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val day = "08"
    val testInput = readInput("day$day/Day${day}_test")
        .map { it.split("|").last() }
        .map { it.trim() }
        .map { it.split(" ") }
        .flatten()

    check(part1(testInput) == 26)
    check(part2(testInput) == 0)

    val input = readInput("day$day/Day$day")
        .map { it.split("|").last() }
        .map { it.trim() }
        .map { it.split(" ") }
        .flatten()
    println(part1(input))
//    println(part2(input))
}


