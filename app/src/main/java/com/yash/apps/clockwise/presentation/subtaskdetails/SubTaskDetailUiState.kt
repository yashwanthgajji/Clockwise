package com.yash.apps.clockwise.presentation.subtaskdetails

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.RecordListItemValue
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task

data class SubTaskDetailUiState(
    val task: Task? = null,
    val subTask: SubTask? = null,
    val recordListItemValues: List<RecordListItemValue> = emptyList(),
)
