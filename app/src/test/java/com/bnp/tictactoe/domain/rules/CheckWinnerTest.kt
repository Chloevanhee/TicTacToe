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
import com.bnp.tictactoe.toPosition
import org.junit.Test

class CheckWinnerTest {
    private lateinit var playTurnUseCase: PlayTurnUseCase

    @Test
    fun `given a board with first cell free, when the x player fills the first cell row, the winner is x`() {
        val board = firstCellFreePositionBoard()
        println("board before: ${board}\n")
        val player = Player.X
        playTurnUseCase = PlayTurnUseCase()
        val gameState = playTurnUseCase(board, 0, 0, player)
        println("board after: ${gameState.board}\n")
        assertThat(gameState.winner).isEqualTo(player)
    }

    @Test
    fun `given a board with first cell free,when the x player fills the cell 2 2, the winner is null and the board is not full`() {
        val board = firstCellFreePositionBoard()
        println("board before: ${board}\n")
        val player = Player.X
        playTurnUseCase = PlayTurnUseCase()
        val gameState = playTurnUseCase(board, 2, 2, player)
        println("board after: ${gameState.board}\n")
        assertThat(gameState.winner).isNull()
    }

    @Test
    fun `given board one turn from victory and board nearly full,when player makes winning turn,then player is declared the winner -not tested position 1,2,6`() {
        val playerList = listOf(
            Player.X,
            null,
            null,
            Player.O,
            Player.X,
            Player.X,
            null,
            Player.X,
            Player.O
        )
        val resultWinnerList = mutableListOf<Player?>()
        val boardBeforeTurnList = mutableListOf<GameBoard>()
        val boardAfterTurnList = mutableListOf<GameBoard>()

        val listOfBoardWithNullAtOnePosition =
            (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }
        listOfBoardWithNullAtOnePosition.forEachIndexed { index, board ->
            if (playerList[index] != null) {
                val (x, y) = index.toPosition(3, 3)
                println("------------------------------------")
                println("index = $index => x = ${x}, y = $y Player = ${playerList[index]}")
                print("board before: ${board}\n")
                boardBeforeTurnList.add(board)
                playTurnUseCase = PlayTurnUseCase()
                val gameState = playTurnUseCase(board, x, y, playerList[index]!!)
                println("board after: ${gameState.board}\n")
                boardAfterTurnList.add(gameState.board)
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
        val boards = (0..8).map { createGameBoardWithEmptyCellAtPosition(it) }.toMutableList()

        boards[0] = boards[0].takeCell(0, 0, Player.X)

        boards.forEachIndexed { index, board ->
            if (index == 0) {
                assertThat(board.isFull()).isTrue()
            } else {
                assertThat(board.isFull()).isFalse()
            }
        }
    }

    @Test
    fun `given horizontal row nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            var board = emptyBoard()
            for (col in 0..2) {
                if (col != i) {
                    board = board.takeCell(col, i, Player.X)
                }
            }
            
            println("board before: ${board}\n")
            val result = playTurn(board, i, i, Player.X)
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player.X)
        }
    }

    @Test
    fun `given vertical row nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            var board = emptyBoard()
            for (row in 0..2) {
                if (row != i) {
                    board = board.takeCell(i, row, Player.X)
                }
            }

            println("board before: ${board}\n")
            val result = playTurn(board, i, i, Player.X)
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player.X)
        }
    }

    @Test
    fun `given diagonal left to right nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            var board = emptyBoard()
            if (i != 0) board = board.takeCell(0, 0, Player.X)
            if (i != 1) board = board.takeCell(1, 1, Player.X)
            if (i != 2) board = board.takeCell(2, 2, Player.X)

            println("board before: ${board}\n")
            val result = playTurn(board, i, i, Player.X)
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player.X)
        }
    }

    @Test
    fun `given diagonal right to left nearly full of the same player, when the last cell is filled, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        for (i in 0..2) {
            var board = emptyBoard()
            if (i != 0) board = board.takeCell(2, 0, Player.X)
            if (i != 1) board = board.takeCell(1, 1, Player.X)
            if (i != 2) board = board.takeCell(0, 2, Player.X)

            println("board before: ${board}\n")
            val x = 2 - i
            val result = playTurn(board, x, i, Player.X)
            println("board after: ${result.board}\n")
            assertThat(result.winner).isEqualTo(Player.X)
        }
    }

    @Test
    fun `given diagonal right to left nearly full of the same player, when the last cell is filled x0 y2, then the winner is the player`() {
        val playTurn = PlayTurnUseCase()
        var board = emptyBoard()
        board = board.takeCell(2, 0, Player.X)
        board = board.takeCell(1, 1, Player.X)
        
        println("board before: ${board}\n")
        val result = playTurn(board, 0, 2, Player.X)
        println("board after: ${result.board}\n")
        assertThat(result.winner).isEqualTo(Player.X)
    }
}
