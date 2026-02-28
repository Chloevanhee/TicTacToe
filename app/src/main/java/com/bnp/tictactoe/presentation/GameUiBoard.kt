package com.bnp.tictactoe.presentation

import androidx.compose.runtime.Immutable
import com.bnp.tictactoe.domain.models.Player

@Immutable
data class BoardItem(val id: Int, val player: Player?)
data class UiGameBoard(
    val boardListItem: List<BoardItem>
)