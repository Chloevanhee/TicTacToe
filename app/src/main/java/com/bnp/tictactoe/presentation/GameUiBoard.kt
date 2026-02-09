package com.bnp.tictactoe.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class BoardItem(val id: Int, val playerCharacter: Char?)
data class UiGameBoard(
    val boardListItem: List<BoardItem>
)