package com.sudhindra.tictactoe.ui.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sudhindra.tictactoe.game.Point
import com.sudhindra.tictactoe.models.Player
import com.sudhindra.tictactoe.viewmodels.GameData
import com.sudhindra.tictactoe.viewmodels.GameState

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    gameData: GameData,
    gameState: GameState,
    players: List<Player>,
    onItemSelect: (Point) -> Unit,
    enabled: Boolean
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        GameGrid(
            players = players,
            selectionMatrix = gameData.points,
            onItemSelect = onItemSelect,
            enabled = enabled
        )
        GameLines(
            Modifier.fillMaxSize(),
            gameState = gameState
        )
    }
}
