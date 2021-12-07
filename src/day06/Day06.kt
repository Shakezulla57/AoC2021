package day06

import readInput

fun main() {
    fun part1(input: List<Int>): Int {
        val fishys = (0..79).fold(input) { acc, i ->
            val babies = acc.filter { it == 0 }.map { 8 }
            acc.map(::decrement).plus(babies)
        }
        return fishys.size
    }

    fun part2(input: List<Int>): Long {
       val sample = LongArray(9).apply {
            input.forEach { fish -> this[fish] += 1L }
        }

        repeat(256) {
            sample.rotateLeftInPlace()
            sample[6] += sample[8]
        }


        return sample.sum()
    }


    // test if implementation meets criteria from the description, like:
    val day = "06"
    val testInput = readInput("day$day/Day${day}_test").first().split(",").map { it.toInt() }
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539L)

    val input = readInput("day$day/Day$day").first().split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}

// put the first element last
fun LongArray.rotateLeftInPlace() {
    val leftmost = first()
    this.copyInto(this, startIndex = 1)
    this[this.lastIndex] = leftmost
}

fun decrement(i: Int): Int {
    return when (i) {
        0 -> 6
        else -> i - 1
    }
}