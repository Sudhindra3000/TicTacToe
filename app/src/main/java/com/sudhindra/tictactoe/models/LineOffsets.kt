package com.sudhindra.tictactoe.models

import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.ui.geometry.Offset

data class LineOffsets(
    val start: Offset,
    val end: Offset
) {
    companion object {
        /**
         * A type converter that converts a LineOffsets to a AnimationVector4D, and vice versa.
         */
        val VectorConverter: TwoWayConverter<LineOffsets, AnimationVector4D>
            get() = LineOffsetsToVector
    }
}

private val LineOffsetsToVector: TwoWayConverter<LineOffsets, AnimationVector4D> =
    TwoWayConverter(
        convertToVector = { (start, end) -> AnimationVector4D(start.x, start.y, end.x, end.y) },
        convertFromVector = {
            LineOffsets(
                start = Offset(it.v1, it.v2),
                end = Offset(it.v3, it.v4)
            )
        }
    )
