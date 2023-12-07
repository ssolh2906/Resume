package com.holsui.myresume.ui.composables

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Dot(dotVisible: Boolean) {
    Surface(
        shape = CircleShape,
        color = if (dotVisible) Color.Gray else Color.Transparent,
        modifier = Modifier
            .size(4.dp)
            .offset(y = 1.dp)
    ) {}
}
