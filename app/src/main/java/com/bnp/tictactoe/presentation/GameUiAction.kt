package com.bnp.tictactoe.presentation

sealed interface GameUiAction {
    data class ClickOnCellBoard(val y: Int, val x: Int) : GameUiAction
    data object RestartGame : GameUiAction
}