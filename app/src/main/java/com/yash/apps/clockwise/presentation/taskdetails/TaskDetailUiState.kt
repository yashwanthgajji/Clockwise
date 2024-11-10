package com.yash.apps.clockwise.presentation.taskdetails

import com.yash.apps.clockwise.domain.model.RecordDetails

data class TaskDetailUiState(
    val recordDetails: List<RecordDetails> = emptyList()
)