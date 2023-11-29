package com.holsui.myresume.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.holsui.myresume.miscellaneous.dpToPx

@Composable
fun SpeechBubble(
    content: @Composable () -> Unit,
    color: Color = Color.White,
    cornerSize: Dp = 8.dp,
    modifier: Modifier,
) {
    val triangleSize = 13
    val visibleTriangleDp = 13.dp
    val context = LocalContext.current
    val triangleSizePx = dpToPx(context, triangleSize + 3f)

    Surface(color = Color.Transparent, modifier = modifier) {
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Surface(
                modifier = Modifier
                    .width(visibleTriangleDp)
                    .fillMaxHeight(),
                shape = TriangleEdgeShape(triangleSizePx.toInt(), dpToPx(context,70f).toInt()),
                color = color,
                border = null,
                content = {}
            )
            Column(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(cornerSize)
                    )
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                content()
            }
        }
    }
}

class TriangleEdgeShape(private val offset: Int, val yOffset: Int = 0) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(x = 0f, y = 0f + yOffset)
            lineTo(x = 0f + offset, y = 0f + yOffset)
            lineTo(x = 0f + offset, y = 0f + offset + yOffset)
        }
        return Outline.Generic(path = trianglePath)
    }
}

@Composable
@Preview
fun PreviewTextRect() {
    SpeechBubble({ Text(text = "Hi") }, modifier = Modifier)
}
