package com.bnp.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCaseInterface
import com.bnp.tictactoe.presentation.utils.toPosition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class GameViewModel(
    private val playTurn: PlayTurnUseCaseInterface
) : ViewModel() {
    private var _state = MutableStateFlow(
        GameUiState()
    )
    val state = _state.asStateFlow()

    fun onAction(action: GameUiAction) {
        when (action) {
            is GameUiAction.ClickOnCellBoard -> {
                if ((state.value.winner == null) && (!state.value.isBoardFull)) {
                    val (x, y) = action.index.toPosition(
                        state.value.board.numberOfLines,
                        state.value.board.numberOfColumns
                    )

                    val gameState = playTurn(state.value.board, x, y, state.value.currentPlayer)
                    _state.value = _state.value.copy(
                        board = gameState.board,
                        isBoardFull = gameState.isBoardFull,
                        winner = gameState.winner,
                        currentPlayer = gameState.currentPlayer
                    )
                }
            }

            GameUiAction.RestartGame -> {
                _state.value = GameUiState()
            }
        }
    }

}



