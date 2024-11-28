package com.yash.apps.clockwise.presentation.taskdetails

import com.yash.apps.clockwise.domain.model.RecordListItemValue
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task

data class TaskDetailUiState(
    val selectedTab: Int = 0,
    val task: Task? = null,
    val recordListItemValues: List<RecordListItemValue> = emptyList(),
    val subTasks: List<SubTask> = emptyList()
)