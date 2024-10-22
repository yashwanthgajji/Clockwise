package com.yash.apps.clockwise.ui.timeline

data class RecordDetails(
    val taskName: String,
    val subTaskName: String? = null,
    val date: Long,
    val duration: String = "00:00:00"
)
