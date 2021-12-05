package day04

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }

        val boards: List<Board> = input.drop(1).filter { it.isNotBlank() }.chunked(5).map {
            it.map { it.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
        }

        val drawn = draws.take(4).toMutableSet()
        return draws.drop(4).firstNotNullOf { draw ->
            drawn.add(draw)
            boards.firstOrNull { it.isWinner(drawn) }?.let { board ->
                draw * board.sumOf { row -> row.filterNot { it in drawn }.sum() }
            }
        }
    }

    fun part2(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }

        val boards: List<Board> = input.drop(1).filter { it.isNotBlank() }.chunked(5).map {
            it.map { it.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
        }

        val drawn = draws.toMutableSet()
        return draws.reversed().firstNotNullOf { draw ->
            drawn.remove(draw)
            boards.firstOrNull { !it.isWinner(drawn) }?.let { board ->
                draw * (board.sumOf { row -> row.filterNot { it in drawn }.sum() } - draw)
            }
        }
    }


    // test if implementation meets criteria from the description, like:
    val day = "04"
    val testInput = readInput("day$day/Day${day}_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("day$day/Day$day")
    println(part1(input))
    println(part2(input))
}

typealias Board = List<List<Int>>

fun Board.isWinner(drawn: Set<Int>): Boolean {
    return this.any { row -> row.all { it in drawn } } ||
            (0..4).any { col -> this.all { row -> row[col] in drawn } }
}