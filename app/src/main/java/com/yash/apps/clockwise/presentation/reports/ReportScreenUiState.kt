package com.yash.apps.clockwise.presentation.reports

import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.util.DateUtil
import java.util.Date

data class ReportScreenUiState(
    val tasks: List<Task> = emptyList(),
    val monthDates: List<Date> = emptyList(),
    val dateSelected: Date = DateUtil.getCurrentDateWithMidnightTime(),
    val todayDate: Date = DateUtil.getCurrentDateWithMidnightTime()
)
