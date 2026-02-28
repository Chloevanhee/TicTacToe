package com.bnp.tictactoe.domain.models

import com.bnp.tictactoe.domain.utils.prettyPrint


data class GameBoard(
    val numberOfColumns: Int = 3,
    val numberOfLines: Int = 3,
    val boardCells: List<List<Player?>> = List(numberOfLines) { List(numberOfColumns) { null } },
) {

    fun takeCell(x: Int, y: Int, player: Player): GameBoard {
        val updatedCells = boardCells.toMutableList().map { it.toMutableList() }
        updatedCells[y][x] = player
        return copy(boardCells = updatedCells)
    }


    fun isValidCell(x: Int, y: Int): Boolean {
        if ((x !in 0..<numberOfColumns) || (y !in 0..<numberOfLines)) {
            return false
        }
        return boardCells[y][x] == null
    }

    fun isFull(): Boolean {
        return boardCells.all { line -> line.all { cell -> cell != null } }
    }

    override fun toString() = prettyPrint()

}