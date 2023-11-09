package com.holsui.myresume

import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

private const val COMPOSE_TEXT_OFFSET_CONSTANT = 0.1f

@Composable
fun TextFieldPDF(
    tag: String,
    defaultString: String = "",
    onTextBoxPlaced: (String, TextInfo) -> Unit = { _, _ -> },
    fontSize: Int
) {
    var currValue by remember { mutableStateOf(defaultString) }

    BasicTextField(
        value = currValue,
        onValueChange = {
            currValue = it
        },
        modifier = Modifier
            .background(color = Color.Yellow)
            .onPlaced { coordinates ->
                onTextBoxPlaced(
                    tag, TextInfo(
                        text = currValue,
                        fontSize = fontSize,
                        x = coordinates.positionInRoot().x,
                        y = coordinates.positionInRoot().y
                    )
                )
            },
        textStyle = TextStyle(
            color = Color.Gray,
            fontSize = fontSize.sp,
            baselineShift = BaselineShift(COMPOSE_TEXT_OFFSET_CONSTANT),
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        ),
    )
}