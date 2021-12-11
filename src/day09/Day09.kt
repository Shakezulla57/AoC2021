package day09

import readInput

fun main() {
    fun part1(input: Array<IntArray>): Int {
        return input.flatMapIndexed { y, rows ->
            rows.mapIndexed { x, column ->
                Point(x, y).takeIf { point ->
                    point.neighbors() // find the neighbors
                        .filter { it in input } // take those that are valid in our input
                        .map { input[it] } // map them into their value - row , then column
                        .all { column < it } // if this value is less than all neighbor values
                }
            }.filterNotNull()
        }.sumOf { input[it] + 1 }
    }

    fun part2(input: Array<IntArray>): Int {
        return input.flatMapIndexed { y, rows ->
            rows.mapIndexed { x, column ->
                Point(x, y).takeIf { point ->
                    point.neighbors() // find the neighbors
                        .filter { it in input } // take those that are valid in our input
                        .map { input[it] } // map them into their value - row , then column
                        .all { column < it } // if this value is less than all neighbor values
                }
            }.filterNotNull()
        }
            .map {  point ->
                val visited = mutableSetOf(point)
                val queue = mutableListOf(point)

                while (queue.isNotEmpty()) {
                    val newNeighbors = queue.removeFirst()
                        .neighbors()
                        .filter { it in input } // take those that are valid in our input
                        .filter { it !in visited }
                        .filter { input[it] != 9 }

                    visited.addAll(newNeighbors)
                    queue.addAll(newNeighbors)
                }

                visited.size
            }
            .sortedDescending()
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val day = "09"
    val testInput = readInput("day$day/Day${day}_test").map { it.map { it.digitToInt() }.toIntArray() }.toTypedArray()

    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("day$day/Day$day").map { it.map { it.digitToInt() }.toIntArray() }.toTypedArray()
    println(part1(input))
    println(part2(input))
}

private operator fun Array<IntArray>.get(point: Point): Int =
    this[point.y][point.x]

private operator fun Array<IntArray>.contains(point: Point): Boolean =
    point.y in this.indices && point.x in this[point.y].indices

data class Point(val x: Int, val y: Int) {
    fun neighbors() = listOf(
        Point(x, y + 1),
        Point(x, y - 1),
        Point(x + 1, y),
        Point(x - 1, y)
    )
}
