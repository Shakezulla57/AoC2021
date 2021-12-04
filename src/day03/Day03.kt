package day03

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.fold(mutableMapOf<Int, List<String>>()) { acc, s ->
            s.forEachIndexed { index, c ->
                acc[index] = (acc[index] ?: mutableListOf()).plus(c.toString())
            }
            acc
        }
            .mapValues {
                mostCommon(it.value)
            }
            .let { map ->
                val gamma = map.values.joinToString(separator = "")
                val epsilon = gamma.map {
                    if (it.toString() == "1") "0" else "1"
                }.joinToString("")
                gamma.toInt(2) * epsilon.toInt(2)
            }
    }

    fun part2(input: List<String>): Int {
        fun findMostCommon(
            values: List<String>,
            preferOne: Boolean = false,
            preferZero: Boolean = false
        ): List<String> {
            return values.fold(mutableMapOf<Int, List<String>>()) { acc, s ->
                s.forEachIndexed { index, c ->
                    acc[index] = (acc[index] ?: mutableListOf()).plus(c.toString())
                }
                acc
            }
                .mapValues {
                    mostCommon(it.value, preferOne, preferZero)
                }
                .map { it.value }
        }

        val oxygen = (0..input.first().lastIndex).fold(input) { acc, i ->
            when (acc.size) {
                2 -> acc.filter { it[i].toString() == "1" }
                1 -> acc
                else -> {
                    val mostCommon = findMostCommon(acc, preferOne = true)[i]
                    acc.filter { it[i].toString() == mostCommon }
                }
            }
        }.let {
            it.first().toInt(2)
        }

        val co2 = (0..input.first().lastIndex).fold(input) { acc, i ->
            val grouped = acc.groupBy { it[i].toString() }
            when {
                acc.size == 1 -> acc
                grouped["0"]?.size == grouped["1"]?.size -> grouped["0"]!!
                else -> {
                    val mostCommon = if (findMostCommon(acc, preferZero = true)[i] == "1") "0" else "1"
                    acc.filter { it[i].toString() == mostCommon }
                }
            }
        }.let {
            it.first().toInt(2)
        }

        return oxygen * co2
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}

fun mostCommon(input: List<String>, preferOne: Boolean = false, preferZero: Boolean = false): String {
    val ones = input.count { it == "1" }
    val zeros = input.count { it == "0" }

    return when {
        ones == zeros -> {
            if (preferOne) "1" else "0"
        }
        ones > zeros -> "1"
        else -> "0"
    }
}