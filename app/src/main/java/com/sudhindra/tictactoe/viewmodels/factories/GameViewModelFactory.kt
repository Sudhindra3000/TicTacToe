package com.sudhindra.tictactoe.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sudhindra.tictactoe.viewmodels.GameViewModel

class GameViewModelFactory(
    private val player1: String,
    private val player2: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(player1, player2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
