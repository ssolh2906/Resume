package com.holsui.myresume

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldPDF(
    tag: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
    defaultString: String = "",
    onTextPlaced: (String, TextInfo) -> Unit = { _, _ -> },
    snapshotState: State<SnapshotState>,
    textStyle: TextStyle = TextStyle.Default
) {
    var currValue by remember { mutableStateOf(defaultString) }

    BasicTextField(
        value = currValue,
        onValueChange = {
            currValue = it
        },
        modifier = modifier
            .width(IntrinsicSize.Min)
            .onPlaced { coordinates ->
                onTextPlaced(
                    tag, TextInfo(
                        text = currValue,
                        fontSize = fontSize,
                        x = coordinates.positionInRoot().x + coordinates.size.width / 2f,
                        y = coordinates.positionInRoot().y
                    )
                )
            },
        textStyle = textStyle.copy(
            color = when (snapshotState.value) {
                SnapshotState.STATE_READY -> Color.Red
                else -> Color.Black
            },
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center
        ),
    )
}