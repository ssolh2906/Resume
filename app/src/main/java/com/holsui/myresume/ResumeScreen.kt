package com.holsui.myresume

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

private const val LETTER_RATIO = 8.5f / 11f

interface ResumeScreenListener {
    fun onGeneratePDFButtonClick() = Unit
    fun onSnapshotReady() = Unit

    fun onAddTextInfo(tag: String, textInfo: TextInfo) = Unit

    companion object {
        val EMPTY = object : ResumeScreenListener {} // For Preview, Test
    }

}

private const val COMPOSE_TEXT_OFFSET_CONSTANT = 0.1f

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ResumeScreen(
    snapshotState: State<SnapshotState>,
    onBitmapSnapshotTaken: (Rect, Bitmap) -> Unit,
    resumeScreenListener: ResumeScreenListener,
) {
    var rectSize by remember { mutableStateOf(IntSize(0, 0)) }
    var captureRect: Rect? by remember { mutableStateOf(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth()
            ) {
                if (snapshotState.value == SnapshotState.STATE_READY) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                    )
                } else {
                    Text(text = "Editing...", modifier = Modifier.fillMaxSize())
                }
            }

            Scaffold(
                modifier = Modifier
                    .aspectRatio(LETTER_RATIO)
                    .onSizeChanged { size ->
                        rectSize = size
                    }
                    .onGloballyPositioned {
                        captureRect = getAbsoluteRect(layoutCoordinate = it, rectSize = rectSize)
                    },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "name",
                                modifier = Modifier.wrapContentSize(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    )
                },
                floatingActionButton = {
                    Button(
                        onClick = { resumeScreenListener.onGeneratePDFButtonClick() },
                    ) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                        Text(text = "PDF")
                    }
                }
            ) { paddingValues ->
                SideEffect {
                    Log.d("SSSSSS", "Side effect, ${snapshotState.value}")
                    when (snapshotState.value) {
                        SnapshotState.STATE_READY -> {
                            resumeScreenListener.onSnapshotReady()
                        }

                        SnapshotState.STATE_CAPTURE -> {

                            captureRect?.let { rect ->
                                val bitmap = Bitmap.createBitmap(
                                    rectSize.width,
                                    rectSize.height,
                                    Bitmap.Config.ARGB_8888
                                )
                                onBitmapSnapshotTaken(rect, bitmap)
                            }

                        }

                        else -> {/* no-op */
                        }
                    }
                }

                Letter(paddingValues, snapshotState)
            }
        }
    }
}

@Composable
private fun Letter(
    paddingValues: PaddingValues,
    snapshotState: State<SnapshotState>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            var text1 by remember { mutableStateOf("가나다라 ABCD") }

            BasicTextField(value = text1,
                onValueChange = {
                    text1 = it
                },
                modifier = Modifier
                    .background(color = Color.Yellow)
                    .onPlaced {
                        Log.d("SSSSSS", "ResumeScreen: onPlaced")
                        Log.d("SSSSSS", "${it.positionInRoot()}")
                    },
                textStyle = TextStyle(
                    color = Color.Gray,
                    fontSize = 50.sp,
                    baselineShift = BaselineShift(COMPOSE_TEXT_OFFSET_CONSTANT),
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                ),
            )

            Text(text = if (snapshotState.value == SnapshotState.STATE_READY) "뤠디ㅣㅣㅣ" else "낫레디",
                modifier = Modifier.onGloballyPositioned {
                })
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

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun PreviewResume() {
    ResumeScreen(
        snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onBitmapSnapshotTaken = { _, _ -> },
        resumeScreenListener = ResumeScreenListener.EMPTY
    )
}