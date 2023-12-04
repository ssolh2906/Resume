package com.holsui.myresume.ui.composables.carditems

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holsui.myresume.R
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.data.TextInfo
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
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .wrapContentHeight()
                    .padding(4.dp)
            ) {
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
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                BSBox(
                    snapshotState = snapshotState,
                    onTextPlaced = onTextPlaced,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, bottom = 4.dp)
                )
            }
            CoursesBox(
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            )
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
        BSHeader("CS", snapshotState, onTextPlaced)
        TextFieldPDF(
            tag = "BSTitle CS",
            defaultString = "Computer Science & Engineering",
            fontSize = 14,
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced,
            isBold = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        BSHeader("Bio", snapshotState, onTextPlaced)
        TextFieldPDF(
            tag = "BSTitle BT",
            defaultString = "Horticultural Biotechnology",
            fontSize = 14,
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced,
            isBold = true
        )
    }

}

@Composable
private fun BSHeader(
    tag: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(painterResource(id = R.drawable.ico_degree), "icon degree", Modifier.size(18.dp))
        TextFieldPDF(
            tag = "BSHeader $tag",
            defaultString = "Bachelor's Degree in",
            fontSize = 12,
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced
        )
    }
}

@Composable
fun CoursesBox(
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
    modifier: Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        color = Color.Transparent,
    ) {
        SpeechBubble(
            content = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    TextFieldPDF(
                        tag = "CoursesHeader",
                        defaultString = "Courses",
                        fontSize = 13,
                        snapshotState = snapshotState,
                        onTextPlaced = onTextPlaced,
                        isBold = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    val courseList = listOf(
                        "OOP",
                        "DataStructure",
                        "Algorithm",
                        "Mobile Programming",
                        "Linux Operating System"
                    )
                    for (course in courseList) {
                        Course(
                            course = course,
                            snapshotState = snapshotState,
                            onTextPlaced = onTextPlaced
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    TextFieldPDF(
                        tag = "courses rear",
                        defaultString = "AND MORE...",
                        fontSize = 10,
                        modifier = Modifier.align(Alignment.End),
                        snapshotState = snapshotState,
                        onTextPlaced = onTextPlaced
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFF8F8F8)
        )
    }
}

@Composable
fun Course(
    course: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Default.Done, null, Modifier.size(12.dp))
        TextFieldPDF(
            tag = "CoursesContent$course",
            defaultString = course,
            fontSize = 11,
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced,
            modifier = Modifier.wrapContentSize()
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
