package com.bnp.tictactoe.domain.rules

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player

fun checkWinner(board: GameBoard): Player? {
    val characterOfTheWinner = getPossibleWinCombinationsHardCoded(board)
    return if (characterOfTheWinner != null) {
        Player(characterOfTheWinner)
    } else {
        null
    }
}

private fun getPossibleWinCombinationsHardCoded(
    board: GameBoard
): Char? {
    println("board $board")
    with(board.boardCells) {
        return if (this[0][0] != null && this[0][0] == this[0][1] && this[0][1] == this[0][2]) {
            this[0][0]
        } else if (this[1][0] != null && this[1][0] == this[1][1] && this[1][1] == this[1][2]) {
            this[1][0]
        } else if (this[2][0] != null && this[2][0] == this[2][1] && this[2][1] == this[2][2]) {
            this[2][0]
        } else if (this[0][0] != null && this[0][0] == this[1][0] && this[1][0] == this[2][0]) {
            this[0][0]
        } else if (this[0][1] != null && this[0][1] == this[1][1] && this[1][1] == this[2][1]) {
            this[0][1]
        } else if (this[0][2] != null && this[0][2] == this[1][2] && this[1][2] == this[2][2]) {
            this[0][2]
        } else if (this[0][0] != null && this[0][0] == this[1][1] && this[1][1] == this[2][2]) {
            this[0][0]
        } else if (this[0][2] != null && this[0][2] == this[1][1] && this[1][1] == this[2][0]) {
            this[0][2]
        } else null
    }
}
