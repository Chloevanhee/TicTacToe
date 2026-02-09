package com.bnp.tictactoe.domain.usecases

import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.GameState
import com.bnp.tictactoe.domain.models.Player
import com.bnp.tictactoe.domain.rules.checkWinner

class PlayTurnUseCase() : PlayTurnUseCaseInterface {

    override operator fun invoke(board: GameBoard, x: Int, y: Int, player: Player): GameState {
        if (!board.isValidCell(x, y)) {
            return GameState(board = board, currentPlayer = player)
        }

        val newBord = board.takeCell(x, y, player.character)
        val checkWinner = checkWinner(newBord)
        val isBoardFull = newBord.isFull()
        val switchPlayer = player.switch()

        return GameState(
            board = newBord,
            currentPlayer = if (checkWinner == null) switchPlayer else player,
            isBoardFull = if (checkWinner == null) isBoardFull else false,
            winner = checkWinner
        )
    }
}
