package com.holsui.myresume

import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

private const val LETTER_RATIO = 8.5f / 11f

interface ResumeClickListener {
    fun onGeneratePDFButtonClick() = Unit

    companion object {
        val EMPTY = object : ResumeClickListener {} // For Preview, Test
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ResumeScreen(
    snapshotState: State<SnapshotState>,
    onBitmapSnapshotTaken: (Rect, Bitmap) -> Unit,
    resumeClickListener: ResumeClickListener,
) {
    var rectSize by remember { mutableStateOf(IntSize(0, 0)) }
    var captureRect: Rect? by remember { mutableStateOf(null) }

    Surface(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .aspectRatio(LETTER_RATIO)
            .onSizeChanged { size ->
                rectSize = size
            }
            .onGloballyPositioned {
                captureRect = getAbsoluteRect(layoutCoordinate = it, rectSize = rectSize)
            },
    ) {
        if (snapshotState.value == SnapshotState.STATE_CAPTURE) {
            captureRect?.let { rect ->
                val bitmap = Bitmap.createBitmap(
                    rectSize.width,
                    rectSize.height,
                    Bitmap.Config.ARGB_8888
                )
                onBitmapSnapshotTaken(rect, bitmap)
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "name",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                )
            },
            floatingActionButton = {
                Button(onClick = {
                    resumeClickListener.onGeneratePDFButtonClick()
                }) {
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                    Text(text = "PDF")
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                Column {
                    Text(text = "메메메메메메")
                    Text(text = "메메메메메메")
                    Text(text = "메메메메메메fdgd")
                    Button(onClick = {}) {
                        Text(text = "메메메메메메")
                    }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png",
                            )
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}


private fun getAbsoluteRect(
    layoutCoordinate: LayoutCoordinates,
    rectSize: IntSize,
): Rect {
    val offsetX = layoutCoordinate.positionInWindow().x
    val offsetY = layoutCoordinate.positionInWindow().y
    val top = offsetY.toInt()
    val left = offsetX.toInt()
    return Rect(left, top, left + rectSize.width, top + rectSize.height)
}