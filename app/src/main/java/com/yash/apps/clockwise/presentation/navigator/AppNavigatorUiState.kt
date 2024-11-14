package com.yash.apps.clockwise.presentation.navigator

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import java.util.Date

data class AppNavigatorUiState(
    val selectedBottomNavigationTab: Int = 0,
    val isActiveSession: Boolean = false,
    val activeDuration: Long = 0,
    val task: Task? = null,
    val subTask: SubTask? = null,
    val startTime: Date = Date(),
    val endTime: Date = Date()
)