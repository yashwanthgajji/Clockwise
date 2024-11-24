package com.yash.apps.clockwise.presentation.reports

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.util.ColorUtil.colors
import com.yash.apps.clockwise.util.DateUtil
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

    var dateSelectorScrollState = LazyListState()
    var isNextEnabled = false

    init {
        _reportScreenUiState.value = _reportScreenUiState.value.copy(
            monthDates = generateMonthDates(reportScreenUiState.value.todayDate)
        )
        fetchReportDataForCurrentDate(reportScreenUiState.value.todayDate)
        dateSelectorScrollState = LazyListState(
            firstVisibleItemIndex = reportScreenUiState.value.let {
                it.monthDates.indexOf(it.todayDate) - 3
            }
        )
        isNextEnabled  = false
    }

    fun updateSelectedDate(date: Date) {
        fetchReportDataForCurrentDate(date)
    }

    fun gotoPrevMonth() {
        val newMonth = DateUtil.gotoPreviousMonth(reportScreenUiState.value.selectedMonth)
        val newMonthDates = generateMonthDates(newMonth)
        val newDate = reportScreenUiState.value.let {
            if (DateUtil.areDatesInSameMonth(newMonth, it.todayDate)) {
                dateSelectorScrollState = LazyListState(
                    firstVisibleItemIndex = newMonthDates.indexOf(it.todayDate) - 3
                )
                isNextEnabled = false
                reportScreenUiState.value.todayDate
            } else {
                dateSelectorScrollState = LazyListState(
                    firstVisibleItemIndex = 0
                )
                isNextEnabled = true
                newMonth
            }
        }
        _reportScreenUiState.value = _reportScreenUiState.value.copy(
            selectedMonth = newMonth,
            dateSelected = newDate,
            monthDates = newMonthDates
        )
        fetchReportDataForCurrentDate(newDate)
    }

    fun gotoNextMonth() {
        val newMonth = DateUtil.gotoNextMonth(reportScreenUiState.value.selectedMonth)
        val newMonthDates = generateMonthDates(newMonth)
        val newDate = reportScreenUiState.value.let {
            if (DateUtil.areDatesInSameMonth(newMonth, it.todayDate)) {
                dateSelectorScrollState = LazyListState(
                    firstVisibleItemIndex = newMonthDates.indexOf(it.todayDate) - 3
                )
                isNextEnabled = false
                reportScreenUiState.value.todayDate
            } else {
                dateSelectorScrollState = LazyListState(
                    firstVisibleItemIndex = 0
                )
                isNextEnabled = true
                newMonth
            }
        }
        _reportScreenUiState.value = _reportScreenUiState.value.copy(
            selectedMonth = newMonth,
            dateSelected = newDate,
            monthDates = newMonthDates
        )
        fetchReportDataForCurrentDate(newDate)
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

    private fun generateMonthDates(date: Date): List<Date> {
        val dates = mutableListOf<Date>()
        val calendar = Calendar.getInstance()
        calendar.time = date
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