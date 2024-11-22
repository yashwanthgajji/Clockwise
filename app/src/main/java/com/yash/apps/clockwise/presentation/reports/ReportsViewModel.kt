package com.yash.apps.clockwise.presentation.reports

import androidx.lifecycle.ViewModel
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {
    private var _reportScreenUiState = MutableStateFlow(ReportScreenUiState())
    var reportScreenUiState: StateFlow<ReportScreenUiState> = _reportScreenUiState.asStateFlow()

    init {
        _reportScreenUiState.value = _reportScreenUiState.value.copy(
            monthDates = generateDates()
        )
    }

    fun updateUiState(uiState: ReportScreenUiState) {
        _reportScreenUiState.value = uiState
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