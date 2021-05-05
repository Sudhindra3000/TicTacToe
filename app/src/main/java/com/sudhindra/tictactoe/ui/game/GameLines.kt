package com.sudhindra.tictactoe.ui.game

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import com.sudhindra.tictactoe.game.WinningLines
import com.sudhindra.tictactoe.game.center
import com.sudhindra.tictactoe.game.d1Line
import com.sudhindra.tictactoe.game.d2Line
import com.sudhindra.tictactoe.game.h1Line
import com.sudhindra.tictactoe.game.h2Line
import com.sudhindra.tictactoe.game.h3Line
import com.sudhindra.tictactoe.game.v1Line
import com.sudhindra.tictactoe.game.v2Line
import com.sudhindra.tictactoe.game.v3Line
import com.sudhindra.tictactoe.models.LineOffsets
import com.sudhindra.tictactoe.viewmodels.GameState

@Composable
fun GameLines(
    modifier: Modifier = Modifier,
    gameState: GameState,
) {
    val lineColor = MaterialTheme.colors.secondary

    var a by remember { mutableStateOf(0f) }

    val line =
        remember {
            Animatable(
                LineOffsets(Offset(0f, 0f), Offset(0f, 0f)),
                LineOffsets.VectorConverter
            )
        }

    LaunchedEffect(gameState) {
        if (gameState is GameState.Won) {
            val start = when (gameState.winningLine) {
                WinningLines.H1 -> a.h1Line().start
                WinningLines.H2 -> a.h2Line().start
                WinningLines.H3 -> a.h3Line().start
                WinningLines.V1 -> a.v1Line().start
                WinningLines.V2 -> a.v2Line().start
                WinningLines.V3 -> a.v3Line().start
                WinningLines.D1 -> a.d1Line().start
                WinningLines.D2 -> a.d2Line().start
                else -> Offset(-1f, -1f)
            }
            val end = when (gameState.winningLine) {
                WinningLines.H1 -> a.h1Line().end
                WinningLines.H2 -> a.h2Line().end
                WinningLines.H3 -> a.h3Line().end
                WinningLines.V1 -> a.v1Line().end
                WinningLines.V2 -> a.v2Line().end
                WinningLines.V3 -> a.v3Line().end
                WinningLines.D1 -> a.d1Line().end
                WinningLines.D2 -> a.d2Line().end
                else -> Offset(-1f, -1f)
            }
            val center = center(start, end)

            line.snapTo(LineOffsets(center, center))
            line.animateTo(LineOffsets(start, end))
        }
    }

    Canvas(modifier) {
        a = size.width
        if (gameState is GameState.Won)
            drawLine(
                lineColor,
                start = line.value.start,
                end = line.value.end,
                strokeWidth = 50f,
                cap = StrokeCap.Round
            )
    }
}