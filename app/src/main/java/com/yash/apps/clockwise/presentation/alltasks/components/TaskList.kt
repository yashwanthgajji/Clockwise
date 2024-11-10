package com.yash.apps.clockwise.presentation.alltasks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.common.IconWithLabel

@Composable
fun TaskList(modifier: Modifier = Modifier, tasks: List<Task>) {
    if (tasks.isEmpty()) {
        IconWithLabel(
            modifier = modifier.fillMaxSize(),
            icon = R.drawable.empty_folder_icon,
            label = "No tasks found"
        )
    } else {
        LazyColumn(
            modifier = modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskCard(task = task)
            }
        }
    }
}