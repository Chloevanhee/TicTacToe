package com.bnp.tictactoe

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player


fun middleOfTheGameBoard(): GameBoard {
    return GameBoard(
        3, 3, listOf(
            listOf(null, null, null),
            listOf(null, Player.X, Player.O),
            listOf(Player.O, Player.X, null)
        )
    )
}

fun firstCellFreePositionBoard(): GameBoard {
    return GameBoard(
        3, 3,
        listOf(
            listOf(null, Player.O, Player.X),
            listOf(Player.O, Player.X, Player.O),
            listOf(Player.O, Player.O, Player.X)
        )
    )
}

fun fullBoardWithWinner(): GameBoard {
    return GameBoard(
        3, 3, listOf(
            listOf(Player.O, Player.O, Player.X),
            listOf(Player.O, Player.X, Player.O),
            listOf(Player.X, Player.O, Player.X)
        )
    )
}


fun fullBoardWithoutWinner(): GameBoard {
    return GameBoard(
        3, 3, listOf(
            listOf(Player.O, Player.X, Player.X),
            listOf(Player.X, Player.O, Player.O),
            listOf(Player.X, Player.O, Player.X)
        )
    )
}

fun emptyBoard(): GameBoard {
    return GameBoard()
}

fun createGameBoardWithEmptyCellAtPosition(emptyCell: Int): GameBoard {
    val board = fullBoardWithoutWinner()
    val (x, y) = emptyCell.toPosition(board.numberOfColumns, board.numberOfLines)

    val updatedCells = board.boardCells.map { it.toMutableList() }.toMutableList()
    updatedCells[y][x] = null

    return board.copy(boardCells = updatedCells)
}
