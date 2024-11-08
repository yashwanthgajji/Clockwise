package com.yash.apps.clockwise.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yash.apps.clockwise.ClockwiseApplication
import com.yash.apps.clockwise.presentation.timeline.TimelineViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            TimelineViewModel(
                taskRepository = inventoryApplication().container.taskRepository,
                subTaskRepository = inventoryApplication().container.subTaskRepository,
                recordRepository = inventoryApplication().container.recordRepository
            )
        }
    }
}

fun CreationExtras.inventoryApplication(): ClockwiseApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ClockwiseApplication)