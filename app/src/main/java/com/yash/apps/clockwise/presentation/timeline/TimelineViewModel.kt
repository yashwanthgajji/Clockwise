package com.yash.apps.clockwise.presentation.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.repository.RecordRepository
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import com.yash.apps.clockwise.domain.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TimelineViewModel(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    private val recordRepository: RecordRepository
): ViewModel() {
    private fun fetchTaskNameById(taskId: Int): String {
        return taskRepository.getTaskNameById(taskId)
    }

    private fun fetchSubTaskNameById(subTaskId: Int?): String {
        return if (subTaskId == null) {
            ""
        } else {
            subTaskRepository.getSubTaskNameById(subTaskId)
        }
    }

    val timelineUiState: StateFlow<TimelineUiState> =
        recordRepository.getRecordByTaskIdStream(1).map { records ->
            TimelineUiState(
                records.groupBy { it.date }.map { (date, recordsForDate) ->
                    TimelineDay(
                        date = date.toString(),
                        recordDetails = recordsForDate.map { record ->
                            RecordDetails(
                                taskName = fetchTaskNameById(record.taskId),
                                subTaskName = fetchSubTaskNameById(record.subTaskId),
                                date = record.date,
                                duration = record.duration
                            )
                        }
                    )
                }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TimelineUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}