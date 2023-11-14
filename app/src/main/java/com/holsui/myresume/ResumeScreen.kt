package com.holsui.myresume

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holsui.myresume.ui.theme.SpacingInset

private const val LETTER_RATIO = 8.5f / 11f

interface ResumeScreenListener {
    fun onGeneratePDFButtonClick() = Unit
    fun onSnapshotReady() = Unit

    fun onAddTextInfo(tag: String, textInfo: TextInfo) = Unit

    companion object {
        val EMPTY = object : ResumeScreenListener {} // For Preview, Test
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ResumeScreen(
    snapshotState: State<SnapshotState>,
    onBitmapSnapshotTaken: (Rect, Bitmap) -> Unit,
    resumeScreenListener: ResumeScreenListener,
) {
    val onTextPlaced = { tag: String, textInfo: TextInfo ->
        resumeScreenListener.onAddTextInfo(tag, textInfo)
    }

    var rectSize by remember { mutableStateOf(IntSize(0, 0)) }
    var captureRect: Rect? by remember { mutableStateOf(null) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Gray)
            ) {
                if (snapshotState.value == SnapshotState.STATE_READY) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                    )
                } else {
                    Text(
                        text = "Editing...",
                        modifier = Modifier.fillMaxSize(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
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
                    MediumTopAppBar(
                        title = {
                            val tag = "HEADING"
                            TextFieldPDF(
                                tag = tag,
                                fontSize = 28,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                defaultString = "SOLHEE TUCKER",
                                onTextPlaced = onTextPlaced,
                                snapshotState = snapshotState,
                                textStyle = TextStyle.Default.copy(fontWeight = FontWeight.Bold)
                            )

                        },
                        navigationIcon = {
                            Row {
                                Icon(Icons.Default.ExitToApp, contentDescription = null)
                            }
                        },
                        actions = {
                            TextFieldPDF(
                                tag = "action",
                                defaultString = "HIRE",
                                fontSize = 22,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(4.dp),
                                snapshotState = snapshotState,
                                textStyle = TextStyle.Default.copy(
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                        },
                        modifier = Modifier
                            .height(64.dp)
                            .padding(horizontal = 8.dp)
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
                    Log.d("SNAPSHOT", "Side effect, ${snapshotState.value}")
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

                LetterContents(
                    paddingValues = paddingValues,
                    snapshotState = snapshotState,
                    onTextPlaced = onTextPlaced
                )
            }
        }
    }
}

@Composable
private fun LetterContents(
    paddingValues: PaddingValues,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(SpacingInset)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
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
@Preview(widthDp = 800, heightDp = 1280)
fun PreviewResume() {
    ResumeScreen(
        snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onBitmapSnapshotTaken = { _, _ -> },
        resumeScreenListener = ResumeScreenListener.EMPTY
    )
}