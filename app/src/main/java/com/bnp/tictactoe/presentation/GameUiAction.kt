package com.bnp.tictactoe.presentation

sealed interface GameUiAction {
    data class ClickOnCellBoard(val index: Int) : GameUiAction
    data object RestartGame : GameUiAction
}