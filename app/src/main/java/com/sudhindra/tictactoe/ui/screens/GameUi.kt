package com.sudhindra.tictactoe.ui.screens

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sudhindra.tictactoe.viewmodels.GameViewModel
import com.sudhindra.tictactoe.viewmodels.factories.GameViewModelFactory

@Composable
fun GameUi(
    player1: String,
    player2: String
) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(player1, player2))
    val gameData by viewModel.gameData.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    val portrait = isPortrait()

    val constraintSet = ConstraintSet {
        val playersScreen = createRefFor("playersScreen")
        val board = createRefFor("board")

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
    }
}
