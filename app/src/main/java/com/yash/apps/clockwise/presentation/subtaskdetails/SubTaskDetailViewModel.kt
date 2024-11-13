package com.yash.apps.clockwise.presentation.subtaskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.RecordListItemValue
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
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
class SubTaskDetailViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
) : ViewModel() {
    private var _subTaskDetailUiState = MutableStateFlow(SubTaskDetailUiState())
    val subTaskDetailUiState: StateFlow<SubTaskDetailUiState> = _subTaskDetailUiState.asStateFlow()

    fun setSubTask(task: Task, subTask: SubTask) {
        _subTaskDetailUiState.value = _subTaskDetailUiState.value.copy(
            task = task,
            subTask = subTask
        )
        fetchAllRecordsBySubTask()
    }

    private fun fetchAllRecordsBySubTask() {
        subTaskDetailUiState.value.subTask?.let {
            viewModelScope.launch {
                recordUseCases.getRecordDetailsBySubTask(it.sId).collect { recordDetails ->
                    val taskRecords = recordDetails
                        .sortedByDescending { it.rDate }
                        .groupBy { it.rDate }
                        .map { (date, records) ->
                            RecordListItemValue(
                                DateFormatter.formatDate(date, FULL_DATE_FORMAT),
                                records.sortedByDescending { it.rStartTime }
                            )
                        }
                    _subTaskDetailUiState.value = _subTaskDetailUiState.value.copy(
                        recordListItemValues = taskRecords
                    )
                }
            }
        }
    }
}