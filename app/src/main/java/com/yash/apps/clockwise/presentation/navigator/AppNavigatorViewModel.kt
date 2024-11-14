package com.yash.apps.clockwise.presentation.navigator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AppNavigatorViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
) : ViewModel() {
    private var _appNavigatorUiState = MutableStateFlow(AppNavigatorUiState())
    val appNavigatorUiState: StateFlow<AppNavigatorUiState> = _appNavigatorUiState.asStateFlow()

    fun update(updatedState: AppNavigatorUiState) {
        _appNavigatorUiState.value = updatedState
    }

    fun startActiveSession(task: Task, subTask: SubTask? = null) {
        _appNavigatorUiState.value = _appNavigatorUiState.value.copy(
            task = task,
            subTask = subTask,
            startTime = Date(),
            isActiveSession = true
        )
    }

    fun stopActiveSession() {
        _appNavigatorUiState.value = _appNavigatorUiState.value.copy(
            isActiveSession = false,
            endTime = Date()
        )
        saveSessionToDb()
    }

    private fun saveSessionToDb() {
        appNavigatorUiState.value.let {
            viewModelScope.launch {
                if (it.task != null) {
                    recordUseCases.insertRecord(
                        Record(
                            rDate = Date(),
                            rStartTime = it.startTime,
                            rEndTime = it.endTime,
                            rDuration = it.endTime.time - it.startTime.time,
                            rTaskId = it.task.tId,
                            rSubTaskId = it.subTask?.sId
                        )
                    )
                }
            }
        }
    }
}