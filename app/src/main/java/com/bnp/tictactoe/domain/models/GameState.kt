package com.bnp.tictactoe.domain.models

data class GameState(
    val board: GameBoard = GameBoard(),
    val currentPlayer: Player = Player('X'),
    val isBoardFull: Boolean = false,
    val winner: Player? = null
)
