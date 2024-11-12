package com.yash.apps.clockwise.presentation.subtaskdetails

import com.yash.apps.clockwise.domain.model.RecordDetails

data class SubTaskDetailUiState(
    val recordDetails: List<RecordDetails> = emptyList(),
)
