package com.bnp.tictactoe.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player
import com.bnp.tictactoe.emptyBoard
import com.bnp.tictactoe.firstCellFreePositionBoard
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class PlayTurnUseCaseTest {

    private lateinit var board: GameBoard
    private lateinit var playTurnUseCase: PlayTurnUseCase
    private lateinit var player: Player

    @Before
    fun setUp() {
        board = emptyBoard()
        playTurnUseCase = PlayTurnUseCase()
        player = Player('X')
    }

    @Test
    fun `when a player placed on a free position, the next player should have switched`(
    ) {
        val player = Player('X')
        playTurnUseCase = PlayTurnUseCase()
        var result = playTurnUseCase(board, 0, 0, player)
        assertThat(result.currentPlayer).isEqualTo(Player('O'))
        result = playTurnUseCase(board, 0, 0, result.currentPlayer)
        assertThat(result.currentPlayer).isEqualTo(Player('X'))
    }

    @Test
    fun `when a player placed on a free position outside the board boundaries, teh player should have switched`(
    ) {
        val player = Player('X')
        playTurnUseCase = PlayTurnUseCase()
        val result = playTurnUseCase(board, -1, 4, player)
        assertThat(result.currentPlayer).isEqualTo(player)
    }

    @Test
    fun `when a player is trying to place on a non-free position, it should return an error`() =
        runTest {
            player = Player('O')
            board = firstCellFreePositionBoard()

            playTurnUseCase = PlayTurnUseCase()
            val result = playTurnUseCase(board, 2, 2, player)
            assertThat(result.currentPlayer).isEqualTo(player)
        }

    @After
    fun tearDown() {
        board = emptyBoard()
        player = Player('X')
        playTurnUseCase = PlayTurnUseCase()

    }

}