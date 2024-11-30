package com.yash.apps.clockwise.presentation.newrecord

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.util.CalendarUtil
import com.yash.apps.clockwise.util.DateUtil
import java.util.Calendar
import java.util.Date

data class NewRecordUiState(
    val date: Date = DateUtil.getCurrentDateWithMidnightTime(),
    val startTime: Calendar = Calendar.getInstance(),
    val endTime: Calendar = Calendar.getInstance(),
    val duration: Long = 0L,
    val task: Task? = null,
    val subTask: SubTask? = null,
    val subTasks: List<SubTask>? = emptyList()
)
