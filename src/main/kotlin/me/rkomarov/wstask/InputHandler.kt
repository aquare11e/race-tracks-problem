package me.rkomarov.wstask

/**
 * Creates Boards list with test cases from input
 */
fun readInput(): List<Board> {
    val numberOfBoards = readLineOfInt(1).first()
    return (1..numberOfBoards).map { readBoard() }
}

private fun readBoard(): Board {
    val boardSize = readLineOfInt(2)

    val startAndFinish = readLineOfInt(4)
    val start = Point(startAndFinish[0], startAndFinish[1])
    val finish = Point(startAndFinish[2], startAndFinish[3])

    val numberOfObstacles = readLineOfInt(1).first()
    val obstacles = (1..numberOfObstacles).map { readObstacle() }

    return Board(boardSize[0], boardSize[1], obstacles, start, finish)
}

private fun readObstacle(): Obstacle {
    val obstacleRanges = readLineOfInt(4)
    val xRange = obstacleRanges[0]..obstacleRanges[1]
    val yRange = obstacleRanges[2]..obstacleRanges[3]

    return Obstacle(xRange, yRange)
}

private fun readLineOfInt(expectedElements: Int): Array<Int> {
    val input = readLine() ?: throw IllegalArgumentException("Empty input")
    val splittedInput = input.trim().split(" ")

    require(splittedInput.size == expectedElements) {
        "Received number of element is different from expected"
    }

    return splittedInput.map { it.toInt() }.toTypedArray()
}
