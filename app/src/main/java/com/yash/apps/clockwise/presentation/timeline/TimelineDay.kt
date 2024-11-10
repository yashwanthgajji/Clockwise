package com.yash.apps.clockwise.presentation.timeline

import com.yash.apps.clockwise.domain.model.RecordDetails

data class TimelineDay(
    val date: String,
    val recordDetails: List<RecordDetails>
)