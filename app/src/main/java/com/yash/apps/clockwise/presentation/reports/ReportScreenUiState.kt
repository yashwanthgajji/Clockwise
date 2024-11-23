package com.yash.apps.clockwise.presentation.reports

import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.util.DateUtil
import java.util.Date

data class ReportScreenUiState(
    val reportDataList: List<ReportDataValue> = emptyList(),
    val monthDates: List<Date> = emptyList(),
    val dateSelected: Date = DateUtil.getCurrentDateWithMidnightTime(),
    val todayDate: Date = DateUtil.getCurrentDateWithMidnightTime()
)
