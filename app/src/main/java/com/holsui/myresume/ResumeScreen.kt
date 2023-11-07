package com.holsui.myresume

import android.graphics.Bitmap
import android.graphics.Rect
import android.nfc.Tag
import android.util.Log
import android.view.PixelCopy
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize

private const val LETTER_RATIO = 8.5f / 11f

const val TAG = "SSSSSS"

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
    onSnapshotTaken: (Rect,Bitmap) -> Unit,
    resumeClickListener: ResumeClickListener,
) {
    val context = LocalContext.current

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
            Log.d(TAG, "ResumeScreen: CAPTURE!")
            captureRect?.let { rect ->
                val bitmap = Bitmap.createBitmap(
                    rectSize.width,
                    rectSize.height,
                    Bitmap.Config.ARGB_8888
                )
                Log.d(TAG, "rect size : $rectSize")
                Log.d(TAG, "bitmap : $bitmap")
//                generatePDF(context = context, bitmap = bitmap)
                onSnapshotTaken(rect, bitmap)
            }
        }

        Scaffold(
            modifier = Modifier.background(Color.Red),
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
                    Log.d(TAG, "ResumeScreen: button click")
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
                    .background(Color.Cyan)
            ) {
                Text(text = "메메메메메메")
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