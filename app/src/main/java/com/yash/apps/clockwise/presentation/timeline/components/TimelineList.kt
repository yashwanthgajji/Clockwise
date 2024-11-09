package com.yash.apps.clockwise.presentation.timeline.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.presentation.timeline.RecordDetails
import com.yash.apps.clockwise.presentation.timeline.TimelineDay
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun TimelineList(days: List<TimelineDay>, modifier: Modifier = Modifier) {
    if (days.isEmpty()) {
        IconWithLabel(
            modifier = Modifier.fillMaxSize(),
            icon = R.drawable.hour_glass,
            label = "Start Working on a task to see timeline"
        )
    } else {
        LazyColumn(
            modifier = modifier,
        ) {
            days.forEach { day ->
                item {
                    DaySectionHeader(dayString = day.date)
                }
                items(day.recordDetails) { recordDetail ->
                    RecordItem(
                        taskName = recordDetail.taskName,
                        subTaskName = recordDetail.subTaskName ?: "",
                        totalDuration = recordDetail.duration,
                        onItemClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TimelineListPreview() {
    val days = remember {
        listOf(
            TimelineDay(
                "Sunday, October 20th, 2024",
                listOf(
                    RecordDetails("Learning", "Leetcode", 1L, "02:31:15"),
                    RecordDetails("Gym", "Cardio", 1L, "00:50:17"),
                    RecordDetails("Course Work", "NLP", 1L, "14:20:02"),
                )
            ),
            TimelineDay(
                "Monday, October 14th, 2024",
                listOf(
                    RecordDetails("Gym", "Triceps", 1L, "01:10:00"),
                    RecordDetails("Learning", "Android Project", 1L, "00:31:15"),
                    RecordDetails("Gym", "Chest", date = 1L),
                )
            ),
            TimelineDay(
                "Saturday, October 12th, 2024",
                listOf(
                    RecordDetails("Course Work", "Database", 1L, "09:40:59"),
                    RecordDetails("Learning", date = 1L),
                )
            )
        )
    }
    ClockwiseTheme {
        TimelineList(days = days)
    }
}