package com.bnp.tictactoe.domain.rules

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.bnp.tictactoe.createGameBoardWithEmptyCellAtPosition
import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.domain.models.Player
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCase
import com.bnp.tictactoe.emptyBoard
import com.bnp.tictactoe.firstCellFreePositionBoard
import com.bnp.tictactoe.presentation.utils.toPosition
import org.junit.Test

class CheckWinnerTest {
    private lateinit var playTurnUseCase: PlayTurnUseCase

    @Test
    fun `given a board with first cell free, when the x player fills the first cell row, the winner is x`() {
        val board = firstCellFreePositionBoard()
        println("board before: ${board}\n")
        val player = Player('X')
        playTurnUseCase = PlayTurnUseCase()
        val gameState = playTurnUseCase(board, 0, 0, player)
        println("board after: ${gameState.board}\n")
        assertThat(gameState.winner).isEqualTo(player)
    }

    @Test
    fun `given a board with first cell free,when the x player fills the cell 2 2, the winner is null and the board is not full`() {
        val board = firstCellFreePositionBoard()
        println("board before: ${board}\n")
        val player = Player('X')
        playTurnUseCase = PlayTurnUseCase()
        val gameState = playTurnUseCase(board, 2, 2, player)
        println("board after: ${gameState.board}\n")
        assertThat(gameState.winner).isNull()
    }

    @Test
    fun `given board one turn from victory and board nearly full,when player makes winning turn,then player is declared the winner -not tested position 1,2,6`() {
        val playerList = listOf(
            Player('X'),
            null, // index 1
            null, // index 2
            Player('O'),
            Player('X'),
            Player('X'),
            null, // index 6
            Player('X'),
            Player('O')
        )
        val resultWinnerList = mutableListOf<Player?>()
        val boardBeforeTurnList = mutableListOf<GameBoard>()
        val boardAfterTurnList = mutableListOf<GameBoard>()

        val listOfBoardWithNullAtOnePosition =
            (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }
        listOfBoardWithNullAtOnePosition.forEachIndexed { index, board ->
            if (playerList[index] != null) {
                val (x, y) = index.toPosition(3, 3)
                val player = Player('X')
                println("------------------------------------")
                println("index = $index => x = ${x}, y = $y Player = $player")
                print("board before: ${board}\n")
                boardBeforeTurnList.add(board)
                playTurnUseCase = PlayTurnUseCase()
                val gameState = playTurnUseCase(board, x, y, playerList[index]!!)
                println("board after: ${gameState.board}\n")
                boardAfterTurnList.add(board)
                resultWinnerList.add(gameState.winner)
                assertThat(gameState.winner).isEqualTo(playerList[index])
            }
        }
    }

    @Test
    fun `given a board full without winner, when a boardCell is set to null at position, the cell is null`() {
        val board = createGameBoardWithEmptyCellAtPosition(4)
        assertThat(board.boardCells[1][1]).isNull()
    }

    @Test
    fun `given a list of board with one null cell at index, when checking the board is full, then returns false - no winner  `() {
        val listOfBoardWithNullAtOnePosition =
            (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }
        listOfBoardWithNullAtOnePosition.forEach { board ->
            println("board after: ${board}\n")
            assertThat(board.isFull()).isFalse()
        }
    }

    @Test
    fun `given a board is not full, when a null cell is at different position, then isFull returns false - no winner`() {
        val listOfBoardWithNullAtOnePosition =
            (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }
        listOfBoardWithNullAtOnePosition.forEach { board ->
            assertThat(board.isFull()).isFalse()
        }
    }

    @Test
    fun `given a board with one null cell, when checking isFull, then returns false - no winner `() {
        val listOfBoardWithNullAtOnePosition =
            (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }
        listOfBoardWithNullAtOnePosition[0].boardCells[0][0] = 'X'
        listOfBoardWithNullAtOnePosition.forEachIndexed { index, board ->
            if (index == 0) {
                assertThat(board.isFull()).isTrue()
            } else {
                assertThat(board.isFull()).isFalse()
            }
        }
    }

    @Test
    fun `given horizontal row nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        lateinit var board: GameBoard
        lateinit var arrayFull: Array<Char?>
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            board = emptyBoard()
            arrayFull = arrayOf('X', 'X', 'X')
            arrayFull[i] = null
            board.boardCells[i] = arrayFull
            println("board before: ${board}\n")
            val (x, y) = (i + 3 * i).toPosition(3, 3)
            println("index = $i => x = ${x}, y = $y")
            val result = playTurn(board, x, y, Player('X'))
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player('X'))
        }
    }

    @Test
    fun `given vertical row nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        lateinit var board: GameBoard
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            board = emptyBoard()
            board.boardCells[0][i] = 'X'
            board.boardCells[1][i] = 'X'
            board.boardCells[2][i] = 'X'
            board.boardCells[i][i] = null
            println("board before: ${board}\n")
            val (x, y) = (i + 3 * i).toPosition(3, 3)
            println("index = $i => x = ${x}, y = $y")
            val result = playTurn(board, x, y, Player('X'))
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player('X'))
        }
    }

    @Test
    fun `given diagonal left to right nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        val board = emptyBoard()
        for (i in 0..2) {
            board.boardCells[0][0] = 'X'
            board.boardCells[1][1] = 'X'
            board.boardCells[2][2] = 'X'
            board.boardCells[i][i] = null
            println("board before: ${board}\n")
            val (x, y) = (i + 3 * i).toPosition(3, 3)
            println("index = $i => x = $x, y = $y")
            val result = playTurn(board, x, y, Player('X'))
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player('X'))
        }
    }

    @Test
    fun `given diagonal right to left nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        val board = emptyBoard()
        for (i in 0..2) {
            board.boardCells[0][2] = 'X'
            board.boardCells[1][1] = 'X'
            board.boardCells[2][0] = 'X'
            board.boardCells[i][2 - i] = null
            println("board before: ${board}\n")
            println("index = $i => x = ${2 - i}, y = $i")
            val result = playTurn(board, 2 - i, i, Player('X'))
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player('X'))
        }
    }

    @Test
    fun `given diagonal right to left nearly full of the same player, when the last cell is filled x0 y2, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        val board = emptyBoard()
        board.boardCells[0][2] = 'X'
        board.boardCells[1][1] = 'X'
        board.boardCells[2][0] = null
        println("board before: ${board}\n")
        println(" x = 0, y = 2")
        val result = playTurn(board, 0, 2, Player('X'))
        println("board after: ${result.board}\n")
        assertThat(result.winner).isEqualTo(Player('X'))

    }

}