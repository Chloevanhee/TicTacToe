package com.bnp.tictactoe

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.presentation.BoardItem
import com.bnp.tictactoe.presentation.UiGameBoard
import com.bnp.tictactoe.presentation.utils.toPosition

fun middleOfTheGameBoard(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf(null, null, null),
            arrayOf(null, 'X', 'O'),
            arrayOf('O', 'X', null)
        )
    )
}

fun firstCellFreePositionBoard(): GameBoard {
    return GameBoard(
        3, 3,
        arrayOf(
            arrayOf(null, 'O', 'X'),
            arrayOf('O', 'X', 'O'),
            arrayOf('O', 'O', 'X')
        )
    )
}

fun fullBoardWithWinner(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf('O', 'O', 'X'),
            arrayOf('O', 'X', 'O'),
            arrayOf('X', 'O', 'X')
        )
    )
}

fun fullBoardWithWinnerdMapped(): UiGameBoard {
    return UiGameBoard(
        listOf(
            BoardItem(1, 'O'),
            BoardItem(2, 'O'),
            BoardItem(3, 'X'),
            BoardItem(4, 'O'),
            BoardItem(5, 'X'),
            BoardItem(6, 'O'),
            BoardItem(7, 'X'),
            BoardItem(8, 'O'),
            BoardItem(9, 'X'),
        )
    )
}

fun fullBoardWithoutWinner(): GameBoard {
    return GameBoard(
        3, 3, arrayOf(
            arrayOf('O', 'X', 'X'),
            arrayOf('X', 'O', 'O'),
            arrayOf('X', 'O', 'X')
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






