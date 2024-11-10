package com.yash.apps.clockwise.presentation.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.util.Constants.FULL_DATE_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {
    private var _timelineUiState = MutableStateFlow(TimelineUiState())
    var timelineUiState: StateFlow<TimelineUiState> = _timelineUiState.asStateFlow()

    init {
        fetchAllRecordDetails()
    }

    private fun fetchAllRecordDetails() {
        viewModelScope.launch {
            recordUseCases.getRecordDetails().collect { recordDetails ->
                _timelineUiState.value = _timelineUiState.value.copy(
                    days = recordDetails.groupBy { it.rDate }.map { (date, recordsForDate) ->
                        TimelineDay(
                            date = DateFormatter.formatDate(date, FULL_DATE_FORMAT),
                            recordDetails = recordsForDate
                        )
                    }
                )
            }
        }
    }
}