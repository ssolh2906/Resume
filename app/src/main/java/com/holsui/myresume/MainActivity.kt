package com.holsui.myresume

import android.os.Bundle
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
                    resumeClickListener = object : ResumeClickListener {
                        override fun onGeneratePDFButtonClick() {
                            viewModel.takeSnapshot()
                        }
                    }) // TODO: Replace to Real value
            }
        }
    }
}
