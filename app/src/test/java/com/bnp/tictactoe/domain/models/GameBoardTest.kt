package com.bnp.tictactoe.domain.models

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.bnp.tictactoe.emptyBoard
import com.bnp.tictactoe.fullBoardWithWinner
import com.bnp.tictactoe.middleOfTheGameBoard
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GameBoardTest {

    private lateinit var gameBoard: GameBoard

    @Before
    fun setUp() {
        gameBoard = emptyBoard()
    }

    @Test
    fun `The party is in the middle of the board occupation and the player want to take position is not empty, a player cannot play on a played position`() =
        runTest {
            val board = middleOfTheGameBoard()
            assertThat(board.isValidCell(-1, -1)).isFalse()
        }

    @Test
    fun `The payers tries to place his character in a position that is not valid`() {
        assertThat(gameBoard.isValidCell(-1, -1)).isFalse()
    }

    @Test
    fun `the board is full, the methode isFull is true`() {
        val board = fullBoardWithWinner()
        assertThat(board.isFull()).isTrue()
    }

    @Test
    fun `the board is not full, the methode isFull is false`() {
        val board = middleOfTheGameBoard()
        assertThat(board.isFull()).isFalse()
    }

}

