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
) : ViewModel() {
    private var _timelineUiState = MutableStateFlow(TimelineUiState())
    var timelineUiState: StateFlow<TimelineUiState> = _timelineUiState.asStateFlow()

    init {
        fetchAllRecordDetails()
    }

    private fun fetchAllRecordDetails() {
        viewModelScope.launch {
            recordUseCases.getRecordDetails().collect { recordDetails ->
                val days = recordDetails.sortedByDescending { it.rDate }
                    .groupBy { it.rDate }.map { (rDate, recordsForDate) ->
                    val groupedBySubTask = recordsForDate
                        .filter { it.sId != null }
                        .groupBy { it.sId }
                        .map { (_, recordsOfSubTask) ->
                            TimelineItem(
                                taskName = recordsOfSubTask[0].tName,
                                subTaskName = recordsOfSubTask[0].sName ?: "",
                                duration = recordsOfSubTask.sumOf { it.rDuration }
                            )
                        }
                    val groupedByTask = recordsForDate
                        .filter { it.sId == null }
                        .groupBy { it.tId }
                        .map { (_, recordsOfTask) ->
                            TimelineItem(
                                taskName = recordsOfTask[0].tName,
                                duration = recordsOfTask.sumOf { it.rDuration }
                            )
                        }
                    TimelineDay(
                        date = DateFormatter.formatDate(rDate, FULL_DATE_FORMAT),
                        recordDetails = (groupedByTask + groupedBySubTask).shuffled()
                    )
                }
                _timelineUiState.value = _timelineUiState.value.copy(
                    days = days
                )
            }
        }
    }
}