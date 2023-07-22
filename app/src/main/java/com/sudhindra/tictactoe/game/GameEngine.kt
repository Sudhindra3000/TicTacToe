package com.sudhindra.tictactoe.game

typealias SelectionArrayMatrix = Array<Array<Selection>>
typealias SelectionListMatrix = List<List<Selection>>

class GameEngine(private val onPointsChange: (SelectionArrayMatrix) -> Unit) {

    private var positions: SelectionArrayMatrix = defaultPositions

    fun select(point: Point, user: String) {
        val (i, j) = point
        positions[i][j] = Selection.Selected(user)
        onPointsChange(positions)
    }

    fun checkWinner(user1: String, user2: String): Pair<String, Int>? {
        // Checking 8 Possible cases of Winning
        for (i in 0..7) {
            val line = when (i) {
                // Horizontal Lines
                0 -> positions[0][0].getUser() + positions[0][1].getUser() + positions[0][2].getUser()
                1 -> positions[1][0].getUser() + positions[1][1].getUser() + positions[1][2].getUser()
                2 -> positions[2][0].getUser() + positions[2][1].getUser() + positions[2][2].getUser()
                // Vertical Lines
                3 -> positions[0][0].getUser() + positions[1][0].getUser() + positions[2][0].getUser()
                4 -> positions[0][1].getUser() + positions[1][1].getUser() + positions[2][1].getUser()
                5 -> positions[0][2].getUser() + positions[1][2].getUser() + positions[2][2].getUser()
                // Diagonal Lines
                6 -> positions[0][0].getUser() + positions[1][1].getUser() + positions[2][2].getUser()
                7 -> positions[0][2].getUser() + positions[1][1].getUser() + positions[2][0].getUser()
                else -> ""
            }
            if (line == "$user1$user1$user1") return Pair(user1, i)
            else if (line == "$user2$user2$user2") return Pair(user2, i)
        }
        return null
    }

    fun resetBoard() {
        positions = defaultPositions
    }

    companion object {
        val defaultPositions: SelectionArrayMatrix = arrayOf(
            arrayOf(Selection.NotSelected, Selection.NotSelected, Selection.NotSelected),
            arrayOf(Selection.NotSelected, Selection.NotSelected, Selection.NotSelected),
            arrayOf(Selection.NotSelected, Selection.NotSelected, Selection.NotSelected)
        )
    }
}

fun <T> Array<Array<T>>.to2DList() = run { map { it.toList() } }

inline fun <T> List<List<T>>.forEachItemIndexed(action: (Point, T) -> Unit) =
    forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, item ->
            action(Point(rowIndex, columnIndex), item)
        }
    }
