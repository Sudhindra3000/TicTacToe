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
import com.sudhindra.tictactoe.game.getWinningLine
import com.sudhindra.tictactoe.models.LineOffsets
import com.sudhindra.tictactoe.viewmodels.GameState

@Composable
fun GameLines(
    modifier: Modifier = Modifier,
    gameState: GameState,
) {
    val lineColor = MaterialTheme.colors.secondary

    var a by remember { mutableStateOf(0f) }

    val lineOffsets =
        remember {
            Animatable(
                LineOffsets(
                    Offset(Float.MAX_VALUE, Float.MAX_VALUE),
                    Offset(Float.MAX_VALUE, Float.MAX_VALUE)
                ),
                LineOffsets.VectorConverter
            )
        }

    LaunchedEffect(gameState) {
        if (gameState is GameState.Won) {
            val offsets = gameState.winningLine.getWinningLine(a)
            val center = offsets.center

            lineOffsets.snapTo(LineOffsets(center, center))
            lineOffsets.animateTo(offsets)
        }
    }

    Canvas(modifier) {
        a = size.width
        if (gameState is GameState.Won)
            drawLine(
                lineColor,
                start = lineOffsets.value.start,
                end = lineOffsets.value.end,
                strokeWidth = 50f,
                cap = StrokeCap.Round
            )
    }
}
