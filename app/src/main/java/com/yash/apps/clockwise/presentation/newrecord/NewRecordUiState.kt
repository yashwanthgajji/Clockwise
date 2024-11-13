package com.yash.apps.clockwise.presentation.newrecord

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import java.util.Calendar
import java.util.Date

data class NewRecordUiState(
    val date: Date? = null,
    val startTime: Calendar? = null,
    val endTime: Calendar? = null,
    val duration: Long? = null,
    val task: Task? = null,
    val subTask: SubTask? = null,
    val subTasks: List<SubTask>? = emptyList()
)
