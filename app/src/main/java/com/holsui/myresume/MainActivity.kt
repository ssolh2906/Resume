package com.holsui.myresume

import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ResumeScreen()
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun ResumeScreen() {
        var rectSize by remember { mutableStateOf(IntSize(0, 0)) }
        var captureRect: Rect? by remember { mutableStateOf(null) }

        Surface(
            modifier = Modifier.fillMaxSize()
                .aspectRatio(8.5f/11f)
                .onSizeChanged { size ->
                    rectSize = size}
                .onGloballyPositioned {
                    captureRect = getAbsoluteRect(layoutCoordinate = it, rectSize = rectSize)
                },
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "PDF Generator",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    )
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    pdfGenerator()
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
}
