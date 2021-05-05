package com.sudhindra.tictactoe.game

import androidx.compose.ui.geometry.Offset
import com.sudhindra.tictactoe.models.LineOffsets

object WinningLines {
    // Horizontal
    const val H1 = 0
    const val H2 = 1
    const val H3 = 2

    // Vertical
    const val V1 = 3
    const val V2 = 4
    const val V3 = 5

    // Diagonal
    const val D1 = 6
    const val D2 = 7
}

// Horizontal
fun Float.h1Line() = LineOffsets(
    start = Offset(this / 6, this / 6),
    end = Offset(5 * this / 6, this / 6)
)

fun Float.h2Line() = LineOffsets(
    start = Offset(this / 6, 3 * this / 6),
    end = Offset(5 * this / 6, 3 * this / 6)
)

fun Float.h3Line() = LineOffsets(
    start = Offset(this / 6, 5 * this / 6),
    end = Offset(5 * this / 6, 5 * this / 6)
)

// Vertical
fun Float.v1Line() = LineOffsets(
    start = Offset(this / 6, this / 6),
    end = Offset(this / 6, 5 * this / 6)
)

fun Float.v2Line() = LineOffsets(
    start = Offset(3 * this / 6, this / 6),
    end = Offset(3 * this / 6, 5 * this / 6)
)

fun Float.v3Line() = LineOffsets(
    start = Offset(5 * this / 6, this / 6),
    end = Offset(5 * this / 6, 5 * this / 6)
)

// Diagonals
fun Float.d1Line() = LineOffsets(
    start = Offset(this / 6, this / 6),
    end = Offset(5 * this / 6, 5 * this / 6)
)

fun Float.d2Line() = LineOffsets(
    start = Offset(5 * this / 6, this / 6),
    end = Offset(this / 6, 5 * this / 6)
)
