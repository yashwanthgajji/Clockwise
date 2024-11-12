package com.yash.apps.clockwise.presentation.taskdetails

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task

data class TaskDetailUiState(
    val selectedTab: Int = 0,
    val task: Task? = null,
    val recordDetails: List<RecordDetails> = emptyList(),
    val subTasks: List<SubTask> = emptyList()
)