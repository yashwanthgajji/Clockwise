package com.yash.apps.clockwise.presentation.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.RecordListItemValue
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.domain.usecases.subtask.SubTaskUseCases
import com.yash.apps.clockwise.util.Constants.FULL_DATE_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases,
    private val subTaskUseCases: SubTaskUseCases
): ViewModel() {
    private var _taskDetailUiState = MutableStateFlow(TaskDetailUiState())
    val taskDetailUiState: StateFlow<TaskDetailUiState> = _taskDetailUiState.asStateFlow()

    fun onTabChanged(index: Int) {
        _taskDetailUiState.value = _taskDetailUiState.value.copy(
            selectedTab = index
        )
    }

    fun setTask(task: Task) {
        _taskDetailUiState.value = _taskDetailUiState.value.copy(
            task = task
        )
        fetchAllRecordsByTask(taskId = task.tId)
        fetchAllSubTasksByTask(taskId = task.tId)
    }

    private fun fetchAllRecordsByTask(taskId: Int) {
        viewModelScope.launch {
            recordUseCases.getRecordDetailsByTask(taskId).collect { recordDetails ->
                val taskRecords = recordDetails
                    .sortedByDescending { it.rDate }
                    .groupBy { it.rDate }
                    .map { (date, records) ->
                        RecordListItemValue(
                            DateFormatter.formatDate(date, FULL_DATE_FORMAT),
                            records.sortedByDescending { it.rStartTime }
                        )
                    }
                _taskDetailUiState.value = _taskDetailUiState.value.copy(
                    recordListItemValues = taskRecords
                )
            }
        }
    }

    private fun fetchAllSubTasksByTask(taskId: Int) {
        viewModelScope.launch {
            subTaskUseCases.getSubTaskByTask(taskId).collect {
                _taskDetailUiState.value = _taskDetailUiState.value.copy(
                    subTasks = it
                )
            }
        }
    }

    fun addNewSubTask(name: String) {
        taskDetailUiState.value.task?.let {
            viewModelScope.launch {
                subTaskUseCases.insertSubTask(SubTask(sName = name, sTaskId = it.tId))
            }
        }
    }
}