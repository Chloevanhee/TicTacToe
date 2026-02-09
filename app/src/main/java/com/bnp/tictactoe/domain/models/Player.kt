package com.bnp.tictactoe.domain.models

data class Player(val character: Char) {
    fun switch() =
        Player(if (character == 'X') 'O' else 'X')
}


