package com.bnp.tictactoe

/**
 * Extension to convert a flat index (0-8) to (x, y) coordinates for a grid.
 */
fun Int.toPosition(numberOfRow: Int, numberOfColumn: Int): Pair<Int, Int> {
    val x = this % numberOfColumn
    val y = this / numberOfRow
    return Pair(x, y)
}
