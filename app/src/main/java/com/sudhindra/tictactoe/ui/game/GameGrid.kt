package com.sudhindra.tictactoe.ui.game

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sudhindra.tictactoe.game.Point
import com.sudhindra.tictactoe.game.Selection
import com.sudhindra.tictactoe.game.SelectionListMatrix
import com.sudhindra.tictactoe.game.forEachItemIndexed
import com.sudhindra.tictactoe.models.Player

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(
    modifier: Modifier = Modifier,
    players: List<Player>,
    selectionMatrix: SelectionListMatrix,
    onItemSelect: (Point) -> Unit,
    enabled: Boolean
) {
    LazyVerticalGrid(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.primary),
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp)
    ) {
        selectionMatrix.forEachItemIndexed { point, selection ->
            item {
                GameCell(
                    Modifier.padding(4.dp),
                    selection = selection,
                    players = players,
                    onClick = {
                        if (selection is Selection.NotSelected && enabled)
                            onItemSelect(point)
                    }
                )
            }
        }
    }
}

@Composable
fun GameCell(
    modifier: Modifier = Modifier,
    selection: Selection,
    players: List<Player>,
    onClick: () -> Unit
) {
    Box(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.primaryVariant)
            .aspectRatio(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (selection is Selection.Selected) {
            println("Piece")
            val piece = players.find { it.id == selection.user }!!.piece
            Text(text = "$piece", style = MaterialTheme.typography.h3, color = Color.White)
        }
    }
}
