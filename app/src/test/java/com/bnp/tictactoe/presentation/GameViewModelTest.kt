package com.bnp.tictactoe.presentation

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import com.bnp.tictactoe.domain.models.Player
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCase
import com.bnp.tictactoe.emptyBoard
import org.junit.Before
import org.junit.Test

class GameViewModelTest {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var playTurnUseCase: PlayTurnUseCase

    @Before
    fun setUp() {
        playTurnUseCase = PlayTurnUseCase()
        gameViewModel = GameViewModel(playTurnUseCase)
    }

    @Test
    fun `given an empty board,when playTurn places player characters then the player switches and GameState updated`() {
        val board = emptyBoard()
        val player = Player('X')
        val stateResult = playTurnUseCase(board, 0, 0, player)
        assertThat(stateResult.currentPlayer).isEqualTo(Player('O'))
        assertThat(stateResult.board.boardCells[0][0]).isEqualTo('X')
        assertThat(stateResult.winner).isNull()
        assertThat(stateResult.isBoardFull).isFalse()
    }

}