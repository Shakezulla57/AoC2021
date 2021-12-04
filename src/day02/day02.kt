package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val split = it.split(" ")
            Movement(
                direction = Direction.valueOf(split.first()),
                distance = split.last().toInt()
            )
        }.fold(0 to 0) { (horizontal, depth), movement ->
            when (movement.direction) {
                Direction.forward -> horizontal + movement.distance to depth
                Direction.down -> horizontal to depth + movement.distance
                Direction.up -> horizontal to depth - movement.distance
            }
        }.let { (horizontal, depth) ->
            horizontal * depth
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val split = it.split(" ")
            Movement(
                direction = Direction.valueOf(split.first()),
                distance = split.last().toInt()
            )
        }.fold(Position()) { (horizontal, depth, aim), movement ->
            when (movement.direction) {
                Direction.forward -> Position(
                    horizontal = horizontal + movement.distance,
                    depth = depth + movement.distance * aim,
                    aim = aim
                )
                Direction.down -> Position(
                    horizontal = horizontal,
                    depth = depth,
                    aim = aim + movement.distance
                )
                Direction.up -> Position(
                    horizontal = horizontal,
                    depth = depth,
                    aim = aim - movement.distance
                )
            }
        }.let { (horizontal, depth) ->
            horizontal * depth
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}

data class Position(
    val horizontal: Int = 0,
    val depth: Int = 0,
    val aim: Int = 0
)

data class Movement(
    val direction: Direction,
    val distance: Int
)

enum class Direction {
    forward,
    down,
    up
}
