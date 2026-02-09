package com.bnp.tictactoe.domain.usecases

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.GameState
import com.bnp.tictactoe.domain.models.Player

interface PlayTurnUseCaseInterface {
    operator fun invoke(board: GameBoard, x: Int, y: Int, player: Player): GameState
}