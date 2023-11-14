package com.holsui.myresume.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
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
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 0.dp
        ),
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
    ) {
        Column {
            SideBarHeader("Contact", snapshotState, onTextPlaced)
            SideBarItem("408-000-0001", snapshotState, onTextPlaced)
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
    onTextPlaced: (String, TextInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 18.dp, horizontal = 16.dp),
    ) {
        Icon(Icons.Default.Call, modifier = Modifier.size(24.dp), contentDescription = "Icon Call")
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
