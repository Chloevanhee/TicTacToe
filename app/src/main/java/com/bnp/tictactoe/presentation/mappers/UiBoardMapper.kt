package com.bnp.tictactoe.presentation.mappers

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.presentation.BoardItem
import com.bnp.tictactoe.presentation.UiGameBoard

fun GameBoard.toBoardUi(): UiGameBoard {
    val list = mutableListOf<BoardItem>()
    var index = 1
    this.boardCells.forEachIndexed { _, y ->
        y.forEachIndexed { _, x ->
            list.add(BoardItem(index, x))
            index++
        }
    }
    return UiGameBoard(list.toList())
}





