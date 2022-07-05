package me.rkomarov.wstask

import java.util.stream.Stream
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RaceTracksSolverTest {
    @ParameterizedTest
    @MethodSource("boardSource")
    fun checkSolver(board: Board, expectedResult: Int?) {
        assertEquals(expectedResult, solveRaceTracksProblem(board))
    }

    companion object {
        @JvmStatic
        fun boardSource(): Stream<Arguments> {
            /*
             Base case with success
                0 0 0 0 F
                0 X X X X
                0 X X X X
                0 0 0 0 0
                0 0 0 0 S
             */

            val board1 = Board(
                5, 5,
                listOf(Obstacle(1..4, 2..3)),
                Point(4, 0),
                Point(4, 4)
            )

            /*
             Base case with fail
                0 X F
                X X X
                A X 0
             */

            val board2 = Board(
                3, 3,
                listOf(
                    Obstacle(1..1, 0..2),
                    Obstacle(0..2, 1..1)
                ),
                Point(0, 0),
                Point(2, 2)
            )

            // Case with start = finish
            val board3 = Board(
                1, 1,
                listOf(),
                Point(0, 0),
                Point(0, 0)
            )

            /*
            Case with hop through the obstacle
                0 0 F 0
                X X X X
                0 S 0 0
                0 0 0 0
             */
            val board4 = Board(
                4, 4,
                listOf(Obstacle(0..3, 2..2)),
                Point(1, 1),
                Point(2, 3)
            )

            /*
            Case with fail caused by the wide obstacle
                0 0 F 0
                X X X X
                X X X X
                0 S 0 0
                0 0 0 0
             */
            val board5 = Board(
                4, 5,
                listOf(Obstacle(0..3, 2..3)),
                Point(1, 1),
                Point(2, 4)
            )

            return Stream.of(
                Arguments.of(board1, 7),
                Arguments.of(board2, null),
                Arguments.of(board3, 0),
                Arguments.of(board4, 4),
                Arguments.of(board5, null)
            )
        }
    }
}
