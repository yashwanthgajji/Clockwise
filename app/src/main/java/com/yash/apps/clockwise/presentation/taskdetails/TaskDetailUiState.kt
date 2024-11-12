package com.yash.apps.clockwise.presentation.taskdetails

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.SubTask

data class TaskDetailUiState(
    val selectedTab: Int = 0,
    val recordDetails: List<RecordDetails> = emptyList(),
    val subTasks: List<SubTask> = emptyList()
)