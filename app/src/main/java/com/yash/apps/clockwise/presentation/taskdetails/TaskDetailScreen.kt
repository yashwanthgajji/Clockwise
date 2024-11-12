package com.yash.apps.clockwise.presentation.taskdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.components.NewTaskFabComponent
import com.yash.apps.clockwise.presentation.common.RecordList
import com.yash.apps.clockwise.presentation.taskdetails.components.SubTaskList
import com.yash.apps.clockwise.presentation.taskdetails.components.TaskDetailTabRow

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel,
    task: Task,
    onSubTaskClick: (SubTask) -> Unit
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    viewModel.fetchAllRecordsByTask(taskId = task.tId)
    viewModel.fetchAllSubTasksByTask(taskId = task.tId)
    val uiState = viewModel.taskDetailUiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AnimatedVisibility(
                selectedTab == 1,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                NewTaskFabComponent(
                    label = "New Sub Task",
                    onSave = { viewModel.addNewSubTask(it, task.tId) }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, top = 32.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = task.tName, style = MaterialTheme.typography.headlineLarge)
            Button(onClick = {}) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    Text(text = "Start Project")
                }
            }
            OutlinedButton(onClick = {}) {
                Text(text = "New Record")
            }
            TaskDetailTabRow(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
            AnimatedContent(targetState = selectedTab, label = "") { tab ->
                when (tab) {
                    0 -> {
                        RecordList(
                            modifier = Modifier.fillMaxSize(),
                            recordDetails = uiState.value.recordDetails
                        )
                    }

                    1 -> {
                        SubTaskList(subTasks = uiState.value.subTasks, onSubTaskClick = onSubTaskClick)
                    }
                }
            }
        }
    }
}