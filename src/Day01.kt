fun main() {
    fun part1(input: List<String>): Int {
        return input.filterIndexed { index, s ->
            if (index == 0) return@filterIndexed false
            s.toInt() > input[index - 1].toInt()
        }.count()

        // better solution
//        return input.map { it.toInt() }
//            .zipWithNext()
//            .count { it.first < it.second }
    }

    fun part2(input: List<String>): Int {
        val summed = (1 until input.lastIndex).mapNotNull { index ->
            if (index - 1 < 0 || index + 1 > input.lastIndex) {
                null
            } else {
                listOf(
                    input[index - 1],
                    input[index],
                    input[index + 1]
                ).sumOf { it.toInt() }
            }
        }
        return summed.filterIndexed { index, s ->
            if (index == 0) return@filterIndexed false
            s > summed[index - 1]
        }.count()

        // better solution
//        return input.map { it.toInt() }
//            .windowed(3, 1) { it.sum() }
//            .zipWithNext()
//            .count { it.first < it.second }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
