package com.holsui.myresume.ui.screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holsui.myresume.R
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.data.TextInfo
import com.holsui.myresume.miscellaneous.getAbsoluteRect
import com.holsui.myresume.ui.composables.EducationCard
import com.holsui.myresume.ui.composables.ExperienceCard
import com.holsui.myresume.ui.composables.FeaturedCard
import com.holsui.myresume.ui.composables.SideBar

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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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

    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current

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
                    Row(
                        modifier = Modifier
                            .background(color = Color(0xFFFEF7FF))
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Top bar icon, menu"
                        )
                        TextFieldPDF(
                            tag = "TopAppBar Center",
                            fontSize = 22,
                            defaultString = "SOLHEE TUCKER",
                            onTextPlaced = onTextPlaced,
                            snapshotState = snapshotState,
                            isBold = true
                        )
                        Row {
                            Icon(
                                painterResource(id = R.drawable.ic_android),
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(8.dp),
                                contentDescription = "Top bar icon, Android"
                            )
                        }
                    }
                },
                floatingActionButton = {
                    if (snapshotState.value == SnapshotState.STATE_IDLE) {
                        Button(
                            onClick = {
                                keyboardController?.hide()
                                view.clearFocus()
                                resumeScreenListener.onGeneratePDFButtonClick()
                            },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            border = BorderStroke(width =1.dp, color = MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                            Text(text = "PDF")
                        }
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

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        SideBar(
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced,
            modifier = Modifier
                .weight(0.32f)
                .padding(vertical = 12.dp)
        )
        Column(
            modifier = Modifier
                .weight(0.68f)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeaturedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            )
            ExperienceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            )
            EducationCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            )
        }
    }
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
