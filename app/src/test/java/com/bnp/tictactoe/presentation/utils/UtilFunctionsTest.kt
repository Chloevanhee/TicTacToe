package com.bnp.tictactoe.presentation.utils

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class UtilFunctionsTest {

    @Test
    fun `test conversion index to row and column position`() {
        val position = (0..8).map { it.toPosition(3, 3) }
        val pairs =
            listOf(0 to 0, 1 to 0, 2 to 0, 0 to 1, 1 to 1, 2 to 1, 0 to 2, 1 to 2, 2 to 2)
        assertThat(position).isEqualTo(pairs)
    }
}