package com.holsui.myresume.ui.composables.carditems

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.data.ExpSpecification
import com.holsui.myresume.data.ExpSpecification.DotDescription
import com.holsui.myresume.data.ExpSpecification.ExpSpecHeader
import com.holsui.myresume.data.TextInfo


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExperienceItem(
    associationName: String,
    description: String? = null,
    dotItems: List<ExpSpecification>? = null,
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
                    fontSize = 15,
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
                dotItems?.forEach { item ->
                    when (item) {
                        is ExpSpecHeader -> DotHeader(
                            item = item,
                            snapshotState = snapshotState,
                            onTextPlaced = onTextPlaced
                        )

                        is DotDescription -> DotDescription(
                            item = item,
                            snapshotState = snapshotState,
                            onTextPlaced = onTextPlaced
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
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
fun DotDescription(
    item: DotDescription,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    val description = item.content
    val dotVisible = item.dot
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = if (dotVisible) Gray else Transparent,
            modifier = Modifier
                .size(4.dp)
                .offset(y = 1.dp)
        ) {}
        Spacer(modifier = Modifier.width(8.dp))
        TextFieldPDF(
            tag = "dot item $description",
            fontSize = 11,
            snapshotState = snapshotState,
            modifier = Modifier.wrapContentSize(),
            defaultString = description,
            onTextPlaced = onTextPlaced
        )
    }
}

@Composable
fun DotHeader(
    item: ExpSpecification,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    val header = item.content
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        TextFieldPDF(
            tag = "dot item $header",
            fontSize = 12,
            snapshotState = snapshotState,
            modifier = Modifier.wrapContentSize(),
            defaultString = header,
            onTextPlaced = onTextPlaced,
            isBold = true
        )
    }
}

@Composable
fun CheckBoxItem(
    text: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    val checkBoxOriginalSize = 20
    val checkBoxScale = 0.7f
    val currCheckBoxSize = checkBoxOriginalSize * checkBoxScale

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFF7F2FA),
        modifier = Modifier
            .height((currCheckBoxSize + 20).dp)
            .padding(end = 4.dp, bottom = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .wrapContentSize()
        ) {
            val context = LocalContext.current
            Checkbox(
                checked = true,
                onCheckedChange = {
                    Toast.makeText(context, "Trust Me, I know $text", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .scale(checkBoxScale)
                    .size(currCheckBoxSize.dp)
                    .padding(start = 6.dp) // Hard coded check box size = 20
            )
            Spacer(modifier = Modifier.width(6.dp))
            TextFieldPDF(
                defaultString = text,
                tag = "checkbox item$text",
                fontSize = 11,
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                modifier = Modifier
                    .padding(end = 6.dp)
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
