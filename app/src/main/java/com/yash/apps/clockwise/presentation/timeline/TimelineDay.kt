package com.yash.apps.clockwise.presentation.timeline

import com.yash.apps.clockwise.util.Constants.DURATION_FORMAT
import com.yash.apps.clockwise.util.DateFormatter.formatDuration

data class TimelineDay(
    val date: String,
    val recordDetails: List<TimelineItem>
)

data class TimelineItem(
    val taskName: String,
    val subTaskName: String = "",
    val duration: Long
) {
    fun getDurationInString(): String {
        return formatDuration(duration, DURATION_FORMAT)
    }
}