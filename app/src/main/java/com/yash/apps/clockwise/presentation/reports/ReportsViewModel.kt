package com.yash.apps.clockwise.presentation.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.util.ColorUtil.colors
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ehsannarmani.compose_charts.models.Pie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
) : ViewModel() {
    private var _reportScreenUiState = MutableStateFlow(ReportScreenUiState())
    var reportScreenUiState: StateFlow<ReportScreenUiState> = _reportScreenUiState.asStateFlow()

    init {
        _reportScreenUiState.value = _reportScreenUiState.value.copy(
            monthDates = generateDates()
        )
        fetchReportDataForCurrentDate(reportScreenUiState.value.todayDate)
    }

    fun updateSelectedDate(date: Date) {
        fetchReportDataForCurrentDate(date)
    }

    private fun getPieDataListFromReportDataList(reportDataList: List<ReportDataValue>): List<Pie> {
        if (reportDataList.isNotEmpty()) {
            val totalDuration = reportDataList.sumOf { it.taskDuration }.toDouble()
            return reportDataList.mapIndexed { index, reportDataValue ->
                Pie(
                    label = reportDataValue.taskName,
                    data = (reportDataValue.taskDuration.toDouble() / totalDuration),
                    color = colors[index % colors.size],
                )
            }
        }
        return emptyList()
    }

    private fun fetchReportDataForCurrentDate(date: Date) {
        viewModelScope.launch {
            recordUseCases.getReportDataOfCurrentData(date).collect { reportDataList ->
                if (reportDataList.isNotEmpty()) {
                    _reportScreenUiState.value = _reportScreenUiState.value.copy(
                        dateSelected = date,
                        reportDataList = reportDataList
                    )
                } else {
                    _reportScreenUiState.value = _reportScreenUiState.value.copy(
                        dateSelected = date,
                        reportDataList = emptyList()
                    )
                }
            }
        }
    }

    private fun generateDates(): List<Date> {
        val dates = mutableListOf<Date>()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (day in 1..maxDay) {
            calendar.set(Calendar.DAY_OF_MONTH, day)
            dates.add(calendar.time)
        }
        return dates
    }
}