package me.rkomarov.wstask

fun main() {
    readInput()
        .map { solveRaceTracksProblem(it) }
        .forEach {
            if (it != null) {
                println("Optimal solution takes $it hops.")
            } else {
                println("No solution.")
            }
        }
}