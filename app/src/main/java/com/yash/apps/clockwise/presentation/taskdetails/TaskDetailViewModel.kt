package com.yash.apps.clockwise.presentation.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.domain.usecases.subtask.SubTaskUseCases
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

    fun fetchAllRecordsByTask(taskId: Int) {
        viewModelScope.launch {
            recordUseCases.getRecordDetailsByTask(taskId).collect {
                _taskDetailUiState.value = _taskDetailUiState.value.copy(
                    recordDetails = it
                )
            }
        }
    }

    fun fetchAllSubTasksByTask(taskId: Int) {
        viewModelScope.launch {
            subTaskUseCases.getSubTaskByTask(taskId).collect() {
                _taskDetailUiState.value = _taskDetailUiState.value.copy(
                    subTasks = it
                )
            }
        }
    }

    fun addNewSubTask(name: String, taskId: Int) {
        viewModelScope.launch {
            subTaskUseCases.insertSubTask(SubTask(sName = name, sTaskId = taskId))
        }
    }
}