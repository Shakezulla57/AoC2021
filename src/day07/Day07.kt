package day07

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<Int>): Int {
        val crabs = input.groupingBy { it }.eachCount()
        val range = crabs.keys.let { keys -> keys.minOf { it }..keys.maxOf { it } }

        fun findSmallest(fuelCost: (Int) -> Int): Int =
            range.minOf { target ->
                crabs.map { (crab, crabCount) ->
                    fuelCost((target - crab).absoluteValue) * crabCount
                }.sum()
            }

        return findSmallest { it }
    }

    fun part2(input: List<Int>): Int {
        val crabs = input.groupingBy { it }.eachCount()
        val range = crabs.keys.let { keys -> keys.minOf { it }..keys.maxOf { it } }

        fun findSmallest(fuelCost: (Int) -> Int): Int =
            range.minOf { target ->
                crabs.map { (crab, crabCount) ->
                    fuelCost((target - crab).absoluteValue) * crabCount
                }.sum()
            }

        return findSmallest { d ->
            (d * (d + 1)) / 2
        }
    }

    // test if implementation meets criteria from the description, like:
    val day = "07"
    val testInput = readInput("day$day/Day${day}_test").first().split(",").map { it.toInt() }
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("day$day/Day$day").first().split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}


