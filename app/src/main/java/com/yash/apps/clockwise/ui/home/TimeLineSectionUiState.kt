package com.yash.apps.clockwise.ui.home

import com.yash.apps.clockwise.model.Task

data class TimeLineSectionUiState(
    val title: String,
    val tasks: List<Task>
)