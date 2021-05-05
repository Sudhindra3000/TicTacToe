package com.sudhindra.tictactoe.ui.game

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sudhindra.tictactoe.models.Player
import com.sudhindra.tictactoe.ui.utils.isPortrait
import com.sudhindra.tictactoe.utils.firstLetter

@Composable
fun PlayersScreen(
    modifier: Modifier = Modifier,
    players: List<Player>,
    currentPlayer: Int
) {
    val content: @Composable () -> Unit = {
        players.forEachIndexed { index, player ->
            PlayerIcon(
                player = player,
                playing = (currentPlayer == index)
            )
        }
    }
    if (isPortrait()) PlayersRow(modifier, content)
    else PlayersColumn(modifier, content)
}

@Composable
fun PlayersRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}

@Composable
fun PlayersColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}

@Composable
fun PlayerIcon(
    modifier: Modifier = Modifier,
    player: Player,
    playing: Boolean
) {
    val transition = updateTransition(targetState = playing, "Player Icon Transition")

    val ringWidth by transition.animateDp(label = "ringWidth") {
        if (it) 3.dp
        else 0.dp
    }
    val ringAlpha by transition.animateFloat(label = "ringWidth") {
        if (it) 1f
        else 0f
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier
                .size(64.dp)
                .border(
                    width = ringWidth,
                    color = MaterialTheme.colors.primary.copy(alpha = ringAlpha),
                    shape = CircleShape
                )
                .background(MaterialTheme.colors.surface, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = player.piece.toString())
        }
        Text(text = player.name, style = MaterialTheme.typography.h6)
    }
}
