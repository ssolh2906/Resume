package com.holsui.myresume

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldPDF(
    tag: String,
    defaultString: String = "",
    onTextPlaced: (String, TextInfo) -> Unit = { _, _ -> },
    fontSize: Int
) {
    var currValue by remember { mutableStateOf(defaultString) }

    BasicTextField(
        modifier = Modifier
            .background(color = Color.Yellow)
            .onPlaced { coordinates ->
                onTextPlaced(
                    tag, TextInfo(
                        text = currValue,
                        fontSize = fontSize,
                        x = coordinates.positionInRoot().x,
                        y = coordinates.positionInRoot().y
                    )
                )
                Log.d("TF_PDF", "TextFieldPDF [P]: ${coordinates.positionInWindow()} ")

            }
            .onGloballyPositioned {coordinates ->
                Log.d("TF_PDF", "TextFieldPDF [G]: ${coordinates.positionInWindow()} ")
            },
        value = currValue,
        onValueChange = {
            currValue = it
        },
        textStyle = TextStyle(
            color = Color.Gray,
            fontSize = fontSize.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        ),
    )
}