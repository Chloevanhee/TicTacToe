package com.bnp.tictactoe.presentation.mappers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.bnp.tictactoe.domain.models.GameBoard
import com.bnp.tictactoe.fullBoardWithWinner
import com.bnp.tictactoe.fullBoardWithWinnerdMapped
import com.bnp.tictactoe.presentation.BoardItem
import org.junit.Test

class UiBoardMapperTest {

    @Test
    fun `map game board from a full board to ui board`() {
        val board = fullBoardWithWinner()
        println("board before: ${board}\n")

        val uiBoard = board.toBoardUi()
        assertThat(uiBoard.boardListItem.size).isEqualTo(9)
        assertThat(uiBoard.boardListItem[0]).isEqualTo(BoardItem(1, 'O'))
        assertThat(uiBoard.boardListItem[4]).isEqualTo(BoardItem(5, 'X'))
        assertThat(uiBoard.boardListItem[8]).isEqualTo(BoardItem(9, 'X'))
        assertThat(uiBoard).isEqualTo(fullBoardWithWinnerdMapped())
    }

    @Test
    fun `map game board from an empty board to ui board`() {
        val board = GameBoard(
            4, 4
        )

        val uiBoard = board.toBoardUi()
        assertThat(uiBoard.boardListItem.size).isEqualTo(16)
        assertThat(uiBoard.boardListItem[0]).isEqualTo(BoardItem(1, null))
        assertThat(uiBoard.boardListItem[15]).isEqualTo(BoardItem(16, null))
    }

}