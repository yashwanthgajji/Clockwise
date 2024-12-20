package com.yash.apps.clockwise.presentation.subtaskdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.common.RecordList

@Composable
fun SubTaskDetailScreen(
    modifier: Modifier = Modifier,
    onNewRecordClick: (Task, SubTask) -> Unit,
    viewModel: SubTaskDetailViewModel,
    onStartClick: (Task, SubTask?) -> Unit,
    isActiveSession: Boolean = false,
    activeSessionComponent: @Composable () -> Unit = {}
) {
    val uiState by viewModel.subTaskDetailUiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, top = 32.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AnimatedVisibility(visible = isActiveSession) {
                activeSessionComponent()
            }
            Text(
                text = uiState.subTask?.sName ?: "Sub Task",
                style = MaterialTheme.typography.headlineLarge
            )
            Button(
                onClick = {
                    uiState.let {
                        if (it.task != null && it.subTask != null) {
                            onStartClick(it.task, it.subTask)
                        }
                    }
                },
                enabled = !isActiveSession
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    Text(text = "Start Project")
                }
            }
            OutlinedButton(onClick = {
                uiState.let {
                    if (it.task != null && it.subTask != null) {
                        onNewRecordClick(it.task, it.subTask)
                    }
                }
            }) {
                Text(text = "New Record")
            }
            RecordList(
                modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
                recordListItemValues = uiState.recordListItemValues,
                isSubTaskList = true
            )
        }
    }
}