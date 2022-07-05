package me.rkomarov.wstask

/**
 * Common class for Point and Velocity
 */
data class Pointable(val x: Int, val y: Int)
typealias Point = Pointable
typealias Velocity = Pointable
operator fun Point.plus(velocity: Velocity) = Point(x + velocity.x, y + velocity.y)

/**
 * Part of solution - current point on the board and current velocity
 */
data class Position(val point: Point, val velocity: Velocity)

/**
 * Obstacle class
 */
data class Obstacle(private val xRange: IntRange, private val yRange: IntRange) {
    fun isInObstacle(point: Point) = point.x in xRange && point.y in yRange
}

/**
 * Board (a.k.a. test case).
 * Represents board with all necessary data to solve the problem.
 */
class Board(
    private val boardXSize: Int,
    private val boardYSize: Int,
    private val obstacles: List<Obstacle>,
    val start: Point,
    val finish: Point
) {
    fun isOnBoard(point: Point) = point.x in 0 until boardXSize && point.y in 0 until boardYSize
    fun isInObstacle(point: Point) = obstacles.any { it.isInObstacle(point) }
}