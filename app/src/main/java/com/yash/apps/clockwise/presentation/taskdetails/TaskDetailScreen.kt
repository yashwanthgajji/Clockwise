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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.alltasks.components.NewTaskFabComponent
import com.yash.apps.clockwise.presentation.common.RecordList
import com.yash.apps.clockwise.presentation.taskdetails.components.SubTaskList
import com.yash.apps.clockwise.presentation.taskdetails.components.TaskDetailTabRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel,
    onNewRecordClick: (Task) -> Unit,
    onSubTaskClick: (Task, SubTask) -> Unit,
    onStartClick: (Task, SubTask?) -> Unit,
    onBackPress: () -> Unit,
    isActiveSession: Boolean = false,
    activeSessionComponent: @Composable () -> Unit = {}
) {
    val uiState by viewModel.taskDetailUiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Go Back"
                        )
                    }

                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = uiState.selectedTab == 1,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                NewTaskFabComponent(
                    label = "New Sub Task",
                    onSave = { viewModel.addNewSubTask(it) }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AnimatedVisibility(visible = isActiveSession) {
                activeSessionComponent()
            }
            Text(
                text = uiState.task?.tName ?: "Task",
                style = MaterialTheme.typography.headlineLarge
            )
            Button(
                onClick = { uiState.task?.let { onStartClick(it, null) } },
                enabled = !isActiveSession
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    Text(text = "Start Task", style = MaterialTheme.typography.bodyLarge)
                }
            }
            OutlinedButton(onClick = { uiState.task?.let { onNewRecordClick(it) } }) {
                Text(text = "New Record", style = MaterialTheme.typography.bodyMedium)
            }
            TaskDetailTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::onTabChanged
            )
            AnimatedContent(targetState = uiState.selectedTab, label = "") { tab ->
                when (tab) {
                    0 -> {
                        RecordList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 16.dp),
                            recordListItemValues = uiState.recordListItemValues
                        )
                    }
                    1 -> {
                        SubTaskList(
                            subTasks = uiState.subTasks,
                            onSubTaskClick = { subTask ->
                                uiState.task?.let { task -> onSubTaskClick(task, subTask) }
                            },
                            onStartClick = { subTask ->
                                uiState.task?.let { task -> onStartClick(task, subTask) }
                            },
                            isActiveSession = isActiveSession
                        )
                    }
                }
            }
        }
    }
}