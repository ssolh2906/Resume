package com.holsui.myresume.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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


@Composable
fun SideBar(
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFFF7F2FA),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 0.dp
        ),
        modifier = modifier
            .fillMaxHeight()
    ) {
        Column {
            SideBarHeader("Contact", snapshotState, onTextPlaced)
            SideBarItem("408-000-0001", snapshotState, onTextPlaced) {
                Icon(
                    Icons.Default.Call,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Icon Call"
                )
            }
            SideBarItem("408-000-0000", snapshotState, onTextPlaced)
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 1.dp)
        }
    }
}

@Composable
fun SideBarHeader(
    string: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 18.dp, horizontal = 16.dp)
    ) {
        TextFieldPDF(
            tag = string,
            fontSize = 14,
            snapshotState = snapshotState,
            defaultString = string,
            onTextPlaced = onTextPlaced,
        )
    }
}

@Composable
fun SideBarItem(
    string: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
    icon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .padding(vertical = 18.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        Spacer(modifier = Modifier.width(12.dp))
        TextFieldPDF(
            tag = string,
            fontSize = 14,
            snapshotState = snapshotState,
            defaultString = string,
            onTextPlaced = onTextPlaced,
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun PreviewSidebar() {
    SideBar(snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onTextPlaced = { _, _ ->

        })
}
