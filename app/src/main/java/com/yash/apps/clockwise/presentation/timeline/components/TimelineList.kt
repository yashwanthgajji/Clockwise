package com.yash.apps.clockwise.presentation.timeline.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.R
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
            days.forEach { day ->
                item {
                    DaySectionHeader(dayString = day.date)
                }
                items(day.recordDetails) { recordDetail ->
                    TimelineRecordItem(
                        taskName = recordDetail.tName,
                        subTaskName = recordDetail.sName ?: "",
                        totalDuration = recordDetail.getDurationInString(),
                        onItemClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}