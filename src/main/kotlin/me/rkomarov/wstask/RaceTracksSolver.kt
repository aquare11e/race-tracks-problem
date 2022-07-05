package me.rkomarov.wstask

fun solveRaceTracksProblem(board: Board): Int? {
    val startPosition = Position(board.start, Velocity(0, 0))

    val positionQueue = ArrayDeque<Position>().apply {
        addLast(startPosition)
    }
    val positionDepth = mutableMapOf(startPosition to 0)

    while (positionQueue.isNotEmpty()) {
        val position = positionQueue.removeFirst()
        val currentDepth = positionDepth[position] ?: throw IllegalStateException("Depth map MUST have a value")

        if (position.point == board.finish) {
            return currentDepth
        }

        for (deltaX in -1..1) {
            for (deltaY in -1..1) {
                val xVelocity = position.velocity.x + deltaX
                val yVelocity = position.velocity.y + deltaY

                if (xVelocity in -3..3 && yVelocity in -3..3) {
                    val currentVelocity = Velocity(xVelocity, yVelocity)
                    val nextPoint = position.point + currentVelocity
                    val nextPosition = Position(nextPoint, currentVelocity)

                    if (board.isOnBoard(nextPoint) && !positionDepth.contains(nextPosition) && !board.isInObstacle(nextPoint)) {
                        positionQueue.addLast(nextPosition)
                        positionDepth[nextPosition] = currentDepth + 1
                    }
                }
            }
        }
    }

    return null
}