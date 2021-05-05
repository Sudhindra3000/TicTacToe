package com.sudhindra.tictactoe.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _showingMultiplayerNamesDialog = MutableStateFlow(false)
    val showingMultiplayerNamesDialog = _showingMultiplayerNamesDialog.asStateFlow()

    fun showDialog() {
        _showingMultiplayerNamesDialog.value = true
    }

    fun hideDialog() {
        _showingMultiplayerNamesDialog.value = false
    }
}
