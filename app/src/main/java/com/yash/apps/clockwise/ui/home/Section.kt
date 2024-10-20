package com.yash.apps.clockwise.ui.home

import com.yash.apps.clockwise.model.Task

data class Section(
    val title: String,
    val tasks: List<Task>
)