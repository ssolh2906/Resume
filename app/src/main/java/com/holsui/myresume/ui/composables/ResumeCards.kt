package com.holsui.myresume.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holsui.myresume.data.SnapshotState
import com.holsui.myresume.TextFieldPDF
import com.holsui.myresume.data.ExpSpecification.*
import com.holsui.myresume.data.TextInfo
import com.holsui.myresume.ui.composables.carditems.EducationItem
import com.holsui.myresume.ui.composables.carditems.ExperienceItem

private const val CARD_ELEVATION = 3
private const val CARD_TITLE_SIZE = 18
private const val FEATURED_CONTENT_SIZE = 13

@Composable
fun FeaturedCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = CARD_ELEVATION.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(4.dp).padding(start = 8.dp)) {
            TextFieldPDF(
                tag = "FeaturedCardTitle",
                fontSize = 14,
                defaultString = "\"PASSIONATE, ADVENTUROUS, PROACTIVE PROGRAMMER\"",
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                isBold = true,
                modifier = Modifier.fillMaxWidth()
            )

            val featuredList = listOf<String>(
                "1yr+ experienced android programmer with strong foundation",
                "Such as OOP, Multithreading, Algorithm, Data Structure",
                "Continuously learning and evolving as a programmer",
                "Migration to Declarative UI, Multi Module App",
                "Competitive. Winning Hackathon, Thesis accepted at academy"
            )
            for (item in featuredList) {
                TextFieldPDF(
                    tag = "featured$item",
                    fontSize = FEATURED_CONTENT_SIZE,
                    snapshotState = snapshotState,
                    defaultString = item,
                    onTextPlaced = onTextPlaced
                )
            }
        }
    }
}

@Composable
fun ExperienceCard(
    modifier: Modifier = Modifier,
    snapshotState: State<SnapshotState>,
    onTextPlaced: (String, TextInfo) -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = CARD_ELEVATION.dp
    ) {
        Column {
            TextFieldPDF(
                tag = "Exp Card Header",
                fontSize = CARD_TITLE_SIZE,
                snapshotState = snapshotState,
                defaultString = "Experience",
                onTextPlaced = onTextPlaced,
                isBold = true,
                modifier = Modifier
                    .padding(4.dp)
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
                    DotDescription(
                        "consistently initiating and participating in productive discussions.",
                        false
                    ),
                    DotDescription("Applied mathematical background to optimize features, solve complex problems "),
                    DotDescription("Bridged the gap between development and design."),
                ),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                description = "AUG 22 - OCT 23, Android Developer, SNS Application with 100K+ Members",
            )
            ExperienceItem(
                associationName = "Gazzi Labs (AI labs)",
                checkBoxItems = listOf("Python", "Batch Programming"),
                dotItems = listOf(
                    DotDescription("Automated Daily Summary using Python, Saving 1hr+/day for all other interns"),
                    DotDescription("Transformed onboarding for non-tech interns, cutting orientation time from 8 to 2 hours"),
                ),
                snapshotState = snapshotState,
                onTextPlaced = onTextPlaced,
                description = "OCT 21 - JAN 22, Data labeling, Student Internship"
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
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF7FF),
        shadowElevation = CARD_ELEVATION.dp
    ) {
        Column(Modifier.fillMaxWidth()) {
            TextFieldPDF(
                tag = "Edu Card Header",
                fontSize = CARD_TITLE_SIZE,
                snapshotState = snapshotState,
                defaultString = "Education",
                onTextPlaced = onTextPlaced,
                isBold = true,
                modifier = Modifier
                    .padding(4.dp)
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

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewFeatured() {
    FeaturedCard(
        snapshotState = mutableStateOf(SnapshotState.STATE_IDLE),
        onTextPlaced = { _, _ ->
        },
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    )
}
