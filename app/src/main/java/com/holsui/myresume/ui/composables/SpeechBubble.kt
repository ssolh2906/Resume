package com.holsui.myresume.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun SpeechBubble(
    content: @Composable () -> Unit,
    color: Color = Color.White
) {
    Surface(color = Color.Transparent) {
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .wrapContentWidth()
                .background(Color.Transparent)
        ) {
            Surface(
                modifier = Modifier
                    .width(13.dp)
                    .fillMaxHeight(),
                shape = TriangleEdgeShape(100),
                color = color,
                border = null,
                content = {}
            )
            Column(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 4.dp,
                            bottomEnd = 4.dp,
                            bottomStart = 4.dp
                        )
                    )
                    .width(100.dp)
            ) {
                Text("Chat")
                content.invoke()
            }
        }
    }
}

class TriangleEdgeShape(private val offset: Int) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(x = 0f, y = 0f)
            lineTo(x = 0f + offset, y = 0f)
            lineTo(x = 0f + offset, y = 0f + offset)
        }
        return Outline.Generic(path = trianglePath)
    }
}

