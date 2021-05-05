package com.sudhindra.tictactoe.viewmodels

import androidx.lifecycle.ViewModel
import com.sudhindra.tictactoe.game.GameEngine
import com.sudhindra.tictactoe.game.Piece
import com.sudhindra.tictactoe.game.Point
import com.sudhindra.tictactoe.game.SelectionListMatrix
import com.sudhindra.tictactoe.game.to2DList
import com.sudhindra.tictactoe.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    player1: String,
    player2: String
) : ViewModel() {

    private val _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState.Playing)
    val gameState = _gameState.asStateFlow()

    private val _gameData = MutableStateFlow(
        GameData(
            points = GameEngine.defaultPositions.to2DList(),
            currentPlayerIndex = 0
        )
    )
    val gameData = _gameData.asStateFlow()

    val players = listOf(
        Player(
            id = "1",
            name = player1,
            piece = Piece.X
        ),
        Player(
            id = "2",
            name = player2,
            piece = Piece.O
        ),
    )
    private val currentPlayer: Player
        get() = players[gameData.value.currentPlayerIndex]

    private val gameEngine: GameEngine = GameEngine(
        onPointsChange = { _gameData.value = _gameData.value.copy(points = it.to2DList()) }
    )

    fun select(point: Point) {
        gameEngine.select(point, currentPlayer.id)
        val result = gameEngine.checkWinner(players[0].id, players[1].id)
        if (result == null)
            togglePayer()
        else
            _gameState.value = GameState.Won(winnerId = result.first, winningLine = result.second)
    }

    private fun togglePayer() {
        val currentPlayerIndex = _gameData.value.currentPlayerIndex
        _gameData.value = _gameData.value.copy(
            currentPlayerIndex = when (currentPlayerIndex) {
                0 -> 1
                else -> 0
            }
        )
    }
}

sealed class GameState {
    object Playing : GameState()
    data class Won(val winnerId: String, val winningLine: Int) : GameState()
    object Tie : GameState()
}

data class GameData(
    val points: SelectionListMatrix,
    var currentPlayerIndex: Int
)
