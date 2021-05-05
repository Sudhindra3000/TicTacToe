package com.sudhindra.tictactoe.game

typealias Point = Pair<Int, Int>

sealed class Selection {
    object NotSelected : Selection()
    data class Selected(val user: String) : Selection()
}

fun Selection.getUser() = when (this) {
    Selection.NotSelected -> ""
    is Selection.Selected -> this.user
}
