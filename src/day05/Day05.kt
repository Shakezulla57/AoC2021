package day05

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val parsed = parseInput(input)
        println(parsed)

       return parsed.filter {
            it.first.x == it.second.x || it.first.y == it.second.y
        }.map { (first, second) ->
            val xDelta = step(first.x, second.x)
            val yDelta = step(first.y, second.y)

            val steps = maxOf((first.x - second.x).absoluteValue, (first.y - second.y).absoluteValue)

            (1..steps).scan(first) { last, _ -> Point(last.x + xDelta, last.y + yDelta) }
        }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }

    fun part2(input: List<String>): Int {
        val parsed = parseInput(input)
        println(parsed)

        return parsed.map { (first, second) ->
            val xDelta = step(first.x, second.x)
            val yDelta = step(first.y, second.y)

            val steps = maxOf((first.x - second.x).absoluteValue, (first.y - second.y).absoluteValue)

            (1..steps).scan(first) { last, _ -> Point(last.x + xDelta, last.y + yDelta) }
        }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }


    // test if implementation meets criteria from the description, like:
    val day = "05"
    val testInput = readInput("day$day/Day${day}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("day$day/Day$day")
    println(part1(input))
    println(part2(input))
}

fun step(a: Int, b: Int): Int =
    when {
        a > b -> -1
        a < b -> 1
        else -> 0
    }

fun parseInput(input: List<String>): List<Pair<Point, Point>> {
    return input.map { s ->
        Pair(
            s.substringBefore(" ").split(",").map { it.toInt() }.let { Point(it.first(), it.last()) },
            s.substringAfterLast(" ").split(",").map { it.toInt() }.let { Point(it.first(), it.last()) }
        )
    }
}

data class Point(val x: Int, val y: Int)