package com.sudhindra.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sudhindra.tictactoe.ui.screens.GameUi
import com.sudhindra.tictactoe.ui.screens.HomeUi
import com.sudhindra.tictactoe.ui.utils.WithTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeUi(navController = navController) }
                    composable("game/{player1}/{player2}") { backStackEntry ->
                        GameUi(
                            players = backStackEntry.arguments?.getString("player1")!!
                                to backStackEntry.arguments?.getString("player2")!!
                        )
                    }
                }
            }
        }
    }
}
