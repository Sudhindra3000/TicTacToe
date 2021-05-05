package com.sudhindra.tictactoe.models

import com.sudhindra.tictactoe.game.Piece

data class Player(
    val id: String,
    val name: String,
    val piece: Piece
)
