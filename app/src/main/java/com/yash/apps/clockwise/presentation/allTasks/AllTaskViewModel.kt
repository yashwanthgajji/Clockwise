package com.yash.apps.clockwise.presentation.allTasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.usecases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
): ViewModel() {
    private var _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            taskUseCases.getTasks().collect {
                _tasks.value = it
            }
        }
    }

    fun addNewTask(taskName: String) {
        viewModelScope.launch {
            taskUseCases.insertTask(Task(name = taskName))
        }
    }
}