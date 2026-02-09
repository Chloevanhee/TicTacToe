package com.bnp.tictactoe.presentation.utils

fun Int.toPosition(numberOfRow: Int, numberOfColumn: Int): Pair<Int, Int> {
    val x = this % numberOfColumn
    val y = this / numberOfRow
    return Pair(x, y)
}
