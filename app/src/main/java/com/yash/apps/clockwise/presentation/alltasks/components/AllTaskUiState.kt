package com.yash.apps.clockwise.presentation.alltasks.components

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task

data class AllTaskUiState(
    val tasks: List<Task> = emptyList(),
    val subTasksMap: Map<Int, List<SubTask>> = mapOf()
)