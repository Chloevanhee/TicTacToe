package com.bnp.tictactoe.domain.models

import com.bnp.tictactoe.domain.utils.prettyPrint


data class GameBoard(
    val numberOfColumns: Int = 3,
    val numberOfLines: Int = 3,
    val boardCells: Array<Array<Char?>> = Array(numberOfLines) { Array(numberOfColumns) { null } },
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameBoard

        if (numberOfColumns != other.numberOfColumns) return false
        if (numberOfLines != other.numberOfLines) return false
        if (!boardCells.contentDeepEquals(other.boardCells)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numberOfColumns
        result = 31 * result + numberOfLines
        result = 31 * result + boardCells.contentDeepHashCode()
        return result
    }

    fun takeCell(x: Int, y: Int, playerCharacter: Char): GameBoard {
        val newBoardCells: Array<Array<Char?>> =
            Array(numberOfLines) { Array(numberOfColumns) { null } }
        for (i in 0 until numberOfLines) {
            for (j in 0 until numberOfColumns) {
                newBoardCells[i][j] = boardCells[i][j]
            }
        }
        newBoardCells[y][x] = playerCharacter
        return GameBoard(numberOfColumns, numberOfLines, newBoardCells)
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