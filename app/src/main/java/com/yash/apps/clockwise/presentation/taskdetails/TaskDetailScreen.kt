package com.yash.apps.clockwise.presentation.taskdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.taskdetails.components.RecordList
import com.yash.apps.clockwise.presentation.taskdetails.components.TaskDetailTabRow

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    taskDetailUiState: TaskDetailUiState,
    task: Task
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
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
                        recordDetails = taskDetailUiState.recordDetails
                    )
                }
                2 -> {}
            }
        }
    }
}