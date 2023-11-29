package com.holsui.myresume.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.data.ExpSpecification
import com.holsui.myresume.data.ExpSpecification.*
import com.holsui.myresume.data.TextInfo
import com.holsui.myresume.ui.composables.carditems.DotHeader
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
                dotItems = listOf(
                    ExpSpecHeader("Dev Performance"),
                    DotDescription("Accelerated UI development with Jetpack Compose"),
                    DotDescription("Reduced build time by 30+% using multi-modularization"),
                    ExpSpecHeader("Testable / Maintainable App"),
                    DotDescription("Ensured app maintainability with Clean Architecture principles "),
                    DotDescription("Increased app flexibility with Dependency injection & Dependency inversion "),
                    DotDescription("Implemented Unit & Instrumented tests "),
                    DotDescription("using APIs as AndroidX Test, Espresso & Compose Test", false),
                    ExpSpecHeader("Team Collaboration"),
                    DotDescription("Established a reputation as a most communicative team member,"),
                    DotDescription("consistently initiating and participating in productive discussions.", false),
                    DotDescription("Applied mathematical background to optimize features, solve complex problems "),
                    DotDescription("Bridged the gap between development and design."),
                ),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                description = "AUG 22 - OCT 23, Android Developer",
            )
            ExperienceItem(
                associationName = "Gazzi Labs (AI labs)",
                checkBoxItems = listOf("Python", "Batch Programming"),
                dotItems = listOf(
                    "Automated Daily Summary using Python, Saving 1hr+/day for all other interns",
                ),
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
                description = "Graduated FEB 23",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced
            )
        }
    }
}

