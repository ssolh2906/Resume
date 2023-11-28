package com.holsui.myresume.ui.composables

import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.holsui.myresume.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.TextInfo
import com.holsui.myresume.ui.composables.carditems.BSBox
import com.holsui.myresume.ui.composables.carditems.CoursesBox
import com.holsui.myresume.ui.composables.carditems.EducationItem
import com.holsui.myresume.ui.composables.carditems.ExperienceItem

@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ) {

    }
}

@Composable
fun FeaturedCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = 1.dp
    ) {
        TextFieldPDF(
            tag = "FeaturedCardTitle",
            fontSize = 22,
            defaultString = "FEATURED",
            snapshotState = snapshotState,
            onTextPlaced = onTextPlaced,
            isBold = true
        )
    }
}

@Composable
fun ExperienceCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = 1.dp
    ) {
        Column {
            TextFieldPDF(
                tag = "Exp Card Header",
                fontSize = 22,
                snapshotState = snapshotState,
                defaultString = "Experience",
                onTextPlaced = onTextPlaced,
                isBold = true,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(start = 8.dp)
            )
            ExperienceItem(
                associationName = "Supergene.CO",
                checkBoxItems = listOf("Kotlin", "AndroidSDK", "JetpackCompose", "MultiThreading"),
                dotItems = listOf(),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                description = "AUG 22 - OCT 23, Android Developer",
            )
            ExperienceItem(
                associationName = "Gazzi Labs (AI labs)",
                checkBoxItems = listOf("Python", "Batch Programming"),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                description = "OCT 21 - JAN 22, Student Internship"
            )
        }
    }
}

@Composable
fun EducationCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = 1.dp
    ) {
        Column(Modifier.fillMaxWidth()) {
            TextFieldPDF(
                tag = "Edu Card Header",
                fontSize = 22,
                snapshotState = snapshotState,
                defaultString = "Education",
                onTextPlaced = onTextPlaced,
                isBold = true,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(start = 8.dp)
            )
            EducationItem(
                associationName = "Kyung Hee University, South Korea",
                description = "FEB 16 - FEB 23",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
        }
    }
}

