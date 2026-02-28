package com.bnp.tictactoe

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player
import com.bnp.tictactoe.presentation.BoardItem
import com.bnp.tictactoe.presentation.UiGameBoard
import com.bnp.tictactoe.presentation.utils.toPosition

fun middleOfTheGameBoard(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf(null, null, null),
            arrayOf(null, Player.X, Player.O),
            arrayOf(Player.O, Player.X, null)
        )
    )
}

fun firstCellFreePositionBoard(): GameBoard {
    return GameBoard(
        3, 3,
        arrayOf(
            arrayOf(null, Player.O, Player.X),
            arrayOf(Player.O, Player.X, Player.O),
            arrayOf(Player.O, Player.O, Player.X)
        )
    )
}

fun fullBoardWithWinner(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf(Player.O, Player.O, Player.X),
            arrayOf(Player.O, Player.X, Player.O),
            arrayOf(Player.X, Player.O, Player.X)
        )
    )
}

fun fullBoardWithWinnerdMapped(): UiGameBoard {
    return UiGameBoard(
        listOf(
            BoardItem(1, Player.O),
            BoardItem(2, Player.O),
            BoardItem(3, Player.X),
            BoardItem(4, Player.O),
            BoardItem(5, Player.X),
            BoardItem(6, Player.O),
            BoardItem(7, Player.X),
            BoardItem(8, Player.O),
            BoardItem(9, Player.X),
        )
    )
}

fun fullBoardWithoutWinner(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf(Player.O, Player.X, Player.X),
            arrayOf(Player.X, Player.O, Player.O),
            arrayOf(Player.X, Player.O, Player.X)
        )
    )
}

fun emptyBoard(): GameBoard {
    return GameBoard(
    )
}

fun createGameBoardWithEmptyCellAtPosition(emptyCell: Int): GameBoard {
    val board = fullBoardWithoutWinner()
    val (x, y) = emptyCell.toPosition(3, 3)
    board.boardCells[y][x] = null
    return board
}






