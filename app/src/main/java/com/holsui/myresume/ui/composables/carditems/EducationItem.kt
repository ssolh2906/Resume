package com.holsui.myresume.ui.composables.carditems

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holsui.myresume.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.TextInfo
import com.holsui.myresume.ui.composables.SpeechBubble

@Composable
fun EducationItem(
    associationName: String,
    description: String? = null,
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 5.dp,
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            Row {
                BSBox(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    snapshotState = snapshotState,
                    onTextPlaced = onTextPlaced
                )
                Surface(
                    modifier = Modifier.width(1.dp),
                    shape = RectangleShape,
                    color = Color.LightGray
                ) {}
                CoursesBox(
                    modifier = Modifier.fillMaxWidth(),
                    snapshotState = snapshotState,
                    onTextPlaced = onTextPlaced
                )

            }
        }
    }
}

@Composable
fun BSBox(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Column(modifier = modifier) {
        TextFieldPDF(
            tag = "BSHeader",
            defaultString = "Bachelors",
            fontSize = 18,
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced
        )
    }

}

@Composable
fun CoursesBox(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(24.dp)
            .wrapContentSize(),
        color = Color.Transparent,
    ) {
        SpeechBubble(
            content = {
                Column(modifier = modifier.wrapContentHeight()) {
                    TextFieldPDF(
                        tag = "CoursesHeader",
                        defaultString = "Courses",
                        fontSize = 16,
                        snapshotState = snapshotState,
                        onTextPlaced = onTextPlaced
                    )
                }
            },
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun PreviewEduItem() {
    EducationItem(
        associationName = "Supergene",
        snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onTextPlaced = { _: String, _: TextInfo -> })
}
