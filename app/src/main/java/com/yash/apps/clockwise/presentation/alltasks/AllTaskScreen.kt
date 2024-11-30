package com.yash.apps.clockwise.presentation.alltasks

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.components.NewTaskFabComponent
import com.yash.apps.clockwise.presentation.alltasks.components.TaskList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AllTaskViewModel,
    onTaskClick: (Task) -> Unit,
    onSubTaskClick: (Task, SubTask) -> Unit,
    bottomBarContent: @Composable () -> Unit,
    onStartClick: (Task, SubTask?) -> Unit,
    isActiveSession: Boolean = false,
    activeSessionComponent: @Composable () -> Unit = {}
) {
    val uiState by viewModel.allTaskUiState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Your Tasks",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },
        bottomBar = bottomBarContent,
        floatingActionButton = {
            NewTaskFabComponent(label = "New Task", onSave = viewModel::addNewTask)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            AnimatedVisibility(visible = isActiveSession) {
                activeSessionComponent()
                Spacer(modifier = Modifier.height(8.dp))
            }
            TaskList(
                tasks = uiState.tasks,
                onTaskClick = onTaskClick,
                subTasksMap = uiState.subTasksMap,
                onSubTaskClick = onSubTaskClick,
                onStartClick = onStartClick,
                isActiveSession = isActiveSession
            )
        }
    }
}

