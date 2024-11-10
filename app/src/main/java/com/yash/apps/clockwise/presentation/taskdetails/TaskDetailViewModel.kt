package com.yash.apps.clockwise.presentation.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {
    private var _taskDetailUiState = MutableStateFlow(TaskDetailUiState())
    val taskDetailUiState: StateFlow<TaskDetailUiState> = _taskDetailUiState.asStateFlow()

    init {
        fetchAllRecordsByTask(1)
    }

    private fun fetchAllRecordsByTask(taskId: Int) {
        viewModelScope.launch {
            recordUseCases.getRecordDetailsByTask(taskId).collect {
                _taskDetailUiState.value = _taskDetailUiState.value.copy(
                    recordDetails = it
                )
            }
        }
    }
}