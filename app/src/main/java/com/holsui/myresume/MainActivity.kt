package com.holsui.myresume

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {

    private val viewModel = ResumeViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ResumeScreen(
                    snapshotState = viewModel.snapshotState.collectAsState(),
                    onBitmapSnapshotTaken = { rect, bitmap ->
                        PixelCopy.request(window, rect, bitmap, {
                            generatePDF(this, rect, bitmap)
                        }, Handler(Looper.getMainLooper()))

                    },
                    resumeClickListener = object : ResumeClickListener {
                        override fun onGeneratePDFButtonClick() {
                            viewModel.takeSnapshot()
                        }
                    })
            }
        }
    }
}
