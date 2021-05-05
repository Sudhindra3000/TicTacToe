package com.sudhindra.tictactoe.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MultiplayerNamesDialog(
    onDismissRequest: () -> Unit,
    onPositiveClick: (String, String) -> Unit,
    onNegativeClick: () -> Unit
) {
    val (player1, setPlayer1) = rememberSaveable { mutableStateOf("") }
    val (player2, setPlayer2) = rememberSaveable { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissRequest) {
        Card(shape = RoundedCornerShape(12.dp)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = player1,
                    onValueChange = setPlayer1,
                    label = { Text(text = "Player 1") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = player2,
                    onValueChange = setPlayer2,
                    label = { Text(text = "Player 2") },
                    singleLine = true
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(modifier = Modifier.weight(1f), onClick = onNegativeClick) {
                        Text(text = "CANCEL", style = MaterialTheme.typography.button)
                    }
                    Button(modifier = Modifier.weight(1f), onClick = {
                        if (player1.isNotBlank() && player2.isNotBlank() && player1 != player2)
                            onPositiveClick(player1, player2)
                    }) {
                        Text(text = "OK", style = MaterialTheme.typography.button)
                    }
                }
            }
        }
    }
}
