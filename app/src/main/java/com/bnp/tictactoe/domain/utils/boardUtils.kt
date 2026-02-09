package com.bnp.tictactoe.domain.utils

import com.bnp.tictactoe.domain.models.GameBoard


fun GameBoard.prettyPrint(): String {
    return buildString {
        appendLine("Board (${numberOfLines}x$numberOfColumns)")
        boardCells.forEach { row ->
            row.forEach { cell ->
                append(" ${cell ?: ' '} |")
            }
            appendLine()
        }
    }
}
