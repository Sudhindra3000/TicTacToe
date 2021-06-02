package com.sudhindra.tictactoe.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudhindra.tictactoe.ui.game.GameBoard
import com.sudhindra.tictactoe.ui.game.PlayersScreen
import com.sudhindra.tictactoe.ui.utils.isPortrait
import com.sudhindra.tictactoe.viewmodels.GameState
import com.sudhindra.tictactoe.viewmodels.GameViewModel
import com.sudhindra.tictactoe.viewmodels.factories.GameViewModelFactory

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameUi(players: Pair<String, String>) {
    val (player1, player2) = players
    val viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(player1, player2))
    val gameData by viewModel.gameData.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    val portrait = isPortrait()

    val constraintSet = ConstraintSet {
        val playersScreen = createRefFor("playersScreen")
        val board = createRefFor("board")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(playersScreen) {
            if (portrait) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top, 50.dp)
            } else {
                centerVerticallyTo(parent)
                start.linkTo(parent.start, 50.dp)
            }
        }
        constrain(board) { centerTo(parent) }
        constrain(bottomButtons) {
            centerHorizontallyTo(parent)
            bottom.linkTo(parent.bottom, 20.dp)
        }
    }
    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
        val responsiveSizeModifier =
            if (portrait) Modifier.fillMaxWidth(0.8f) else Modifier.fillMaxHeight(0.8f)

        PlayersScreen(
            Modifier
                .layoutId("playersScreen")
                .then(responsiveSizeModifier),
            players = viewModel.players,
            currentPlayer = gameData.currentPlayerIndex
        )
        GameBoard(
            Modifier
                .layoutId("board")
                .then(responsiveSizeModifier)
                .aspectRatio(1f),
            gameData = gameData,
            gameState = gameState,
            players = viewModel.players,
            onItemSelect = { viewModel.select(it) },
            enabled = true
        )
        if (gameState !is GameState.Playing)
            GameBottomButtons(
                Modifier.layoutId("bottomButtons"),
                onPlayAgainClick = {},
                onEndGameClick = {}
            )
    }
}

@Composable
fun GameBottomButtons(
    modifier: Modifier = Modifier,
    onPlayAgainClick: () -> Unit,
    onEndGameClick: () -> Unit
) {
    Row(modifier) {
        Button(
            onClick = onPlayAgainClick,
            shape = CircleShape,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp)
        ) {
            Text(text = "PLAY AGAIN", style = MaterialTheme.typography.h6)
        }
        Spacer(Modifier.size(20.dp))
        Button(
            onClick = onEndGameClick,
            shape = CircleShape,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp)
        ) {
            Text(text = "END GAME", style = MaterialTheme.typography.h6)
        }
    }
}
