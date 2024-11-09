package com.yash.apps.clockwise.presentation.allTasks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.presentation.allTasks.components.NewTaskFabComponent
import com.yash.apps.clockwise.presentation.allTasks.components.TaskList

@Composable
fun AllTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AllTaskViewModel,
    bottomBarContent: @Composable () -> Unit
) {
    val allTasks = viewModel.tasks.collectAsState()
    Scaffold(
        bottomBar = bottomBarContent,
        floatingActionButton = {
            NewTaskFabComponent(onSave = viewModel::addNewTask)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        TaskList(modifier = modifier.padding(innerPadding), tasks = allTasks.value)
    }
}

