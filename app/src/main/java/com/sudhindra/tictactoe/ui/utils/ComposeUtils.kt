package com.sudhindra.tictactoe.ui.utils

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.sudhindra.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun WithTheme(content: @Composable () -> Unit) {
    TicTacToeTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun isPortrait() = (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
