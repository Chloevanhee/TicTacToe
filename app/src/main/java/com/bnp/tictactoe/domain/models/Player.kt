package com.bnp.tictactoe.domain.models


enum class Player {
    X,
    O;

    fun switch(): Player = if (this == X) O else X
}


