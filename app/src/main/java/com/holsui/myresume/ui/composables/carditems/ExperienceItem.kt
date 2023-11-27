package com.holsui.myresume.ui.composables.carditems

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holsui.myresume.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.TextInfo


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExperienceItem(
    associationName: String,
    description: String? = null,
    dotItems: List<String>? = null,
    checkBoxItems: List<String>? = null,
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
            Column {
                TextFieldPDF(
                    tag = associationName + "Title",
                    fontSize = 16,
                    snapshotState = snapshotState,
                    onTextPlaced = onTextPlaced,
                    defaultString = associationName,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .wrapContentSize()
                        .padding(4.dp),
                    isBold = true
                )
                description?.let {
                    TextFieldPDF(
                        tag = associationName + "Description",
                        defaultString = it,
                        fontSize = 12,
                        snapshotState = snapshotState,
                        onTextPlaced = onTextPlaced,
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                    )
                }
                FlowRow {
                    if (checkBoxItems != null) {
                        for (item in checkBoxItems) {
                            CheckBoxItem(
                                text = item,
                                snapshotState = snapshotState,
                                onTextPlaced = onTextPlaced
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckBoxItem(
    text: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFF7F2FA),
        modifier = Modifier
            .padding(end = 4.dp, bottom = 2.dp)
            .height(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentSize()
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {},
            )
            TextFieldPDF(
                defaultString = text,
                tag = "checkbox item$text",
                fontSize = 14,
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .wrapContentSize()
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun PreviewExpItem() {
    ExperienceItem(associationName = "Supergene",
        checkBoxItems = listOf(
            "Android SDK",
            "Kotlin",
        ),
        snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onTextPlaced = { _: String, _: TextInfo -> })
}
