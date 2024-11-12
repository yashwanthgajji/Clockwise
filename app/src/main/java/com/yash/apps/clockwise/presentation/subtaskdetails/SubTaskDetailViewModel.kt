package com.yash.apps.clockwise.presentation.subtaskdetails

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
class SubTaskDetailViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {
    private var _subTaskDetailUiState = MutableStateFlow(SubTaskDetailUiState())
    val subTaskDetailUiState: StateFlow<SubTaskDetailUiState> = _subTaskDetailUiState.asStateFlow()

    fun fetchAllRecordsBySubTask(subTaskId: Int) {
        viewModelScope.launch {
            recordUseCases.getRecordDetailsBySubTask(subTaskId).collect {
                _subTaskDetailUiState.value = _subTaskDetailUiState.value.copy(
                    recordDetails = it
                )
            }
        }
    }
}