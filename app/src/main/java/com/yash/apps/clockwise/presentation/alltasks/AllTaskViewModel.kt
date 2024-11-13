package com.yash.apps.clockwise.presentation.alltasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.usecases.subtask.SubTaskUseCases
import com.yash.apps.clockwise.domain.usecases.task.TaskUseCases
import com.yash.apps.clockwise.presentation.alltasks.components.AllTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val subTaskUseCases: SubTaskUseCases
): ViewModel() {
    private var _allTaskUiState = MutableStateFlow(AllTaskUiState())
    val allTaskUiState: StateFlow<AllTaskUiState> = _allTaskUiState.asStateFlow()

    init {
        fetchAllTasks()
        fetchAllSubTasks()
    }

    private fun fetchAllTasks() {
        viewModelScope.launch {
            taskUseCases.getTasks().collect {
                _allTaskUiState.value = _allTaskUiState.value.copy(
                    tasks = it
                )
            }
        }
    }

    private fun fetchAllSubTasks() {
        viewModelScope.launch {
            subTaskUseCases.getSubTasks().collect { subTasks ->
                val subTasksMap = subTasks.groupBy { it.sTaskId }
                _allTaskUiState.value = _allTaskUiState.value.copy(
                    subTasksMap = subTasksMap
                )
            }
        }
    }

    fun addNewTask(taskName: String) {
        viewModelScope.launch {
            taskUseCases.insertTask(Task(tName = taskName))
        }
    }
}