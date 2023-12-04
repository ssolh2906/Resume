package com.holsui.myresume.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Divider
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

const val SIDE_ITEMS_PADDING = 8
const val DESCRIPTION_FONT_SIZE = 10
const val DESCRIPTION_PADDING = 40

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
            SideBarItem("solheetucker@gmail.com", snapshotState, onTextPlaced) {
                Icon(
                    Icons.Default.MailOutline,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp),
                    contentDescription = "Icon Call"
                )
            }
            SideBarItem("669)-271-2586", snapshotState, onTextPlaced) {
                Icon(
                    Icons.Default.Call,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Icon Call"
                )
            }
            SideBarDivider()

            SideBarHeader("Address", snapshotState, onTextPlaced)
            SideBarItem("(95032) Los Gatos, CA, USA", snapshotState, onTextPlaced)

            SideBarDivider()

            SideBarHeader("Links", snapshotState, onTextPlaced)
            SideBarItem("github.com/ssolh2906", snapshotState, onTextPlaced) {
                Icon(
                    painterResource(id = R.drawable.ico_github),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp),
                    contentDescription = "Icon Call"
                )
            }
            SideBarItem(
                "www.linkedin.com/in/SolPT/",
                snapshotState,
                onTextPlaced
            ) {
                Icon(
                    painterResource(id = R.drawable.ico_linkedin),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp),
                    contentDescription = "Icon Call"
                )
            }
            SideBarItem(
                "holsui.tistory.com",
                snapshotState,
                onTextPlaced
            ) {
                Icon(
                    painterResource(id = R.drawable.ico_blog),
                    modifier = Modifier
                        .size(24.dp),
                    contentDescription = "Icon Call"
                )
            }

            SideBarDivider()

            SideBarHeader(
                string = "Language",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )

            SideBarItem(
                string = "English",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            ) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
            SideBarItem(
                string = "Korean",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            ) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }

            SideBarDivider()

            SideBarHeader(
                string = "Accomplishments",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarItem(
                string = "Thesis Accepted",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            ) {
                Icon(
                    painterResource(id = R.drawable.ico_paper),
                    contentDescription = "Icon paper",
                    modifier = Modifier.size(24.dp)
                )
            }
            SideBarDescription(
                string = "2022 Korean Computer Comprehensive",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarDescription(
                string = "Academic Conference",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarDescription(
                string = "\'Smart farm water management system",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarDescription(
                string = "using weight sensors\'",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarItem(
                string = "#1 Hackathon", snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            ) {
                Icon(
                    painterResource(id = R.drawable.ico_trophy),
                    contentDescription = "Icon trophy",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(1.dp)
                )
            }

            SideBarDescription(
                string = "Silicon Valley Koreans",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )

            SideBarDescription(
                string = "2023 요즘 애들 MZ's Hackathon",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
            SideBarDescription(
                string = "UI/UX Improvement using location",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )

            SideBarItem(
                string = "Dean's List", snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
            ) {
                Icon(
                    painterResource(id = R.drawable.ico_award),
                    contentDescription = "Icon Award",
                    modifier = Modifier.size(24.dp)
                )
            }
            SideBarDivider()
            Surface(
                color = Color.White, modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column {
                    TextFieldPDF(
                        tag = "bottomMessage",
                        fontSize = 10,
                        snapshotState = snapshotState,
                        defaultString = "This resume is implemented with",
                        onTextPlaced = onTextPlaced,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(top = 4.dp)
                    )
                    TextFieldPDF(
                        tag = "bottomMessage",
                        fontSize = 10,
                        snapshotState = snapshotState,
                        defaultString = "Android SDK & Jetpack Compose",
                        onTextPlaced = onTextPlaced,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Column {
                            TextFieldPDF(
                                tag = "check out project",
                                fontSize = 12,
                                snapshotState = snapshotState,
                                onTextPlaced = onTextPlaced,
                                defaultString = "CHECK OUT",
                                isBold = true
                            )

                            TextFieldPDF(
                                tag = "check out project",
                                fontSize = 12,
                                snapshotState = snapshotState,
                                onTextPlaced = onTextPlaced,
                                defaultString = "Project Resume",
                                isBold = true
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.qr_project),
                            contentDescription = "QR code for project",
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SideBarDescription(
    string: String,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit
) {
    TextFieldPDF(
        tag = "thesis",
        defaultString = string,
        fontSize = DESCRIPTION_FONT_SIZE,
        snapshotState = snapshotState,
        onTextPlaced = onTextPlaced,
        modifier = Modifier.padding(start = DESCRIPTION_PADDING.dp)
    )
}

@Composable
private fun SideBarDivider() {
    Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 1.dp)
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
            .padding(vertical = SIDE_ITEMS_PADDING.dp, horizontal = 16.dp)
    ) {
        TextFieldPDF(
            tag = string,
            fontSize = 16,
            snapshotState = snapshotState,
            defaultString = string,
            onTextPlaced = onTextPlaced,
            isBold = true
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
            .padding(top = SIDE_ITEMS_PADDING.dp, bottom = SIDE_ITEMS_PADDING.dp, start = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        Spacer(modifier = Modifier.width(12.dp))
        TextFieldPDF(
            tag = string,
            fontSize = 13,
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
