package com.bnp.tictactoe.presentation

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player

data class GameUiState(
    val board: GameBoard = GameBoard(),
    val isBoardFull: Boolean = false,
    val winner: Player? = null,
    val currentPlayer: Player = Player('X'),
)
