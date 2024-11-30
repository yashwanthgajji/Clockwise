package com.yash.apps.clockwise.presentation.timeline.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.presentation.common.DaySectionHeader
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.presentation.timeline.TimelineDay

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
            items(days) { day ->
                DaySectionHeader(dayString = day.date)
                repeat(day.recordDetails.size) { index ->
                    val recordDetail = day.recordDetails[index]
                    TimelineRecordItem(
                        taskName = recordDetail.taskName,
                        subTaskName = recordDetail.subTaskName,
                        totalDuration = recordDetail.getDurationInString(),
                        onItemClick = { /*TODO*/ }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}