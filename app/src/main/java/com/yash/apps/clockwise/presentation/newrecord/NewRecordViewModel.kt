package com.yash.apps.clockwise.presentation.newrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.domain.usecases.subtask.SubTaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewRecordViewModel @Inject constructor(
    private val subTaskUseCases: SubTaskUseCases,
    private val recordUseCases: RecordUseCases
): ViewModel() {
    private var _newRecordUiState = MutableStateFlow(NewRecordUiState())
    val newRecordUiState: StateFlow<NewRecordUiState> = _newRecordUiState.asStateFlow()

    fun onSubTaskChange(subTask: SubTask?) {
        _newRecordUiState.value = _newRecordUiState.value.copy(
            subTask = subTask
        )
    }

    fun onDateChange(dateInMillis: Long) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateInMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        _newRecordUiState.value = _newRecordUiState.value.copy(
            date = calendar.time
        )
    }

    fun onStartTimeChange(startTimeInCalendar: Calendar) {
        _newRecordUiState.value = _newRecordUiState.value.copy(
            startTime = startTimeInCalendar
        )
    }

    fun onEndTimeChange(endTimeInCalendar: Calendar) {
        _newRecordUiState.value = _newRecordUiState.value.copy(
            endTime = endTimeInCalendar
        )
    }

    fun setTaskAndSubTask(task: Task, subTask: SubTask?) {
        _newRecordUiState.value = _newRecordUiState.value.copy(
            task = task,
            subTask = subTask
        )
        fetchSubTasksByTask()
    }

    fun saveRecord(): Boolean {
        _newRecordUiState.value.let {
            if (it.date != null && it.task != null && it.startTime != null && it.endTime != null) {
                val record = Record(
                    rDate = it.date,
                    rStartTime = it.startTime.time,
                    rEndTime = it.endTime.time,
                    rDuration = it.endTime.time.time - it.startTime.time.time,
                    rTaskId = it.task.tId,
                    rSubTaskId = it.subTask?.sId
                )
                viewModelScope.launch {
                    recordUseCases.insertRecord(record)
                }
                return true
            } else {
                return false
            }
        }
    }

    private fun fetchSubTasksByTask() {
        _newRecordUiState.value.task?.let { task ->
            viewModelScope.launch {
                subTaskUseCases.getSubTaskByTask(task.tId).collect() {
                    _newRecordUiState.value = _newRecordUiState.value.copy(
                        subTasks = it
                    )
                }
            }
        }
    }
}