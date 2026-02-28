package com.bnp.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.bnp.tictactoe.domain.models.GameState
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCaseInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class GameViewModel(
    private val playTurn: PlayTurnUseCaseInterface
) : ViewModel() {
    private var _state = MutableStateFlow(
        GameState()
    )
    val state = _state.asStateFlow()

    fun onAction(action: GameUiAction) {
        when (action) {
            is GameUiAction.ClickOnCellBoard -> {
                if ((state.value.winner == null) && (!state.value.isBoardFull)) {
                    val gameState = playTurn(
                        state.value.board,
                        action.x,
                        action.y,
                        state.value.currentPlayer
                    )
                    _state.value = _state.value.copy(
                        board = gameState.board,
                        isBoardFull = gameState.isBoardFull,
                        winner = gameState.winner,
                        currentPlayer = gameState.currentPlayer
                    )
                }
            }

            GameUiAction.RestartGame -> {
                _state.value = GameState()
            }
        }
    }

}



