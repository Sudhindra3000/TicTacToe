package com.sudhindra.tictactoe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.sudhindra.tictactoe.ui.dialogs.MultiplayerNamesDialog
import com.sudhindra.tictactoe.viewmodels.HomeViewModel

private val title = """
    TIC
    TAC
    TOE
""".trimIndent()

@Composable
fun HomeUi(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController,
) {
    if (viewModel.showingMultiplayerNamesDialog.collectAsState().value)
        MultiplayerNamesDialog(
            onDismissRequest = viewModel::hideDialog,
            onPositiveClick = { player1, player2 ->
                navController.navigate("game/$player1/$player2") {
                    popUpTo("home") { inclusive = true }
                }
            },
            onNegativeClick = viewModel::hideDialog
        )
    HomePortraitUi(onMultiPlayerClick = viewModel::showDialog)
}

@Composable
fun HomePortraitUi(
    onMultiPlayerClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
        HomeMenu(
            Modifier.fillMaxWidth(0.85f),
            onSinglePlayerClick = {},
            onMultiPlayerClick = onMultiPlayerClick,
            onOnlineMultiPlayerClick = {}
        )
    }
}

@Composable
fun HomeLandscapeUi(navController: NavHostController) {
    val navigate = { navController.navigate("game") }
    Row(
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
        HomeMenu(
            Modifier.fillMaxWidth(0.5f),
            onSinglePlayerClick = navigate,
            onMultiPlayerClick = navigate,
            onOnlineMultiPlayerClick = navigate
        )
    }
}

@Composable
fun HomeMenu(
    modifier: Modifier = Modifier,
    onSinglePlayerClick: () -> Unit,
    onMultiPlayerClick: () -> Unit,
    onOnlineMultiPlayerClick: () -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
    ) {
        HomeButton(
            Modifier.fillMaxWidth(),
            text = "Single Player",
            onClick = onSinglePlayerClick
        )
        HomeButton(
            Modifier.fillMaxWidth(),
            text = "Local Multiplayer",
            onClick = onMultiPlayerClick
        )
        HomeButton(
            Modifier.fillMaxWidth(),
            text = "Online Multiplayer",
            onClick = onOnlineMultiPlayerClick
        )
    }
}

@Composable
fun HomeButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(15.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.h5)
    }
}
