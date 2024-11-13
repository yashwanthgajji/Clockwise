package com.yash.apps.clockwise.presentation.alltasks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.components.NewTaskFabComponent
import com.yash.apps.clockwise.presentation.alltasks.components.TaskList

@Composable
fun AllTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AllTaskViewModel,
    onTaskClick: (Task) -> Unit,
    onSubTaskClick: (Task, SubTask) -> Unit,
    bottomBarContent: @Composable () -> Unit
) {
    val uiState = viewModel.allTaskUiState.collectAsState()
    Scaffold(
        bottomBar = bottomBarContent,
        floatingActionButton = {
            NewTaskFabComponent(label = "New Task", onSave = viewModel::addNewTask)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        TaskList(
            modifier = modifier.padding(innerPadding),
            tasks = uiState.value.tasks,
            onTaskClick = onTaskClick,
            subTasksMap = uiState.value.subTasksMap,
            onSubTaskClick = onSubTaskClick
        )
    }
}

