package com.yash.apps.clockwise.presentation.allTasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun AllTaskScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        TaskList(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TaskList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {

    }
}

@Composable
fun TaskCard(
    isPressed: Boolean,
    onCardPress: (Boolean) -> Unit,
    task: Task,
    subTasks: List<SubTask>,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        modifier = modifier
            .clickable { onCardPress(!isPressed) }
            .clip(MaterialTheme.shapes.large)
    ) {
        Column {
            TaskItem(name = task.name, isSubTask = false)
            HorizontalDivider(thickness = 2.dp)
            if (isPressed) {
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(subTasks) { subTask ->
                        TaskItem(name = subTask.name, isSubTask = true)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(name: String, isSubTask: Boolean, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = name, modifier = Modifier.weight(1f))
        IconButton(
            onClick = { },
            modifier = Modifier
                .clip(MaterialTheme.shapes.small),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "start Task $name",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
            )
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .clip(MaterialTheme.shapes.small),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = "start Task $name",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
            )
        }
    }
}

@Composable
fun NewTaskButton(onClick:() -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            Text(text = "New Task")
        }
    }
}

@Preview
@Composable
fun NewTaskButtonPreview() {
    ClockwiseTheme {
        NewTaskButton(onClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    ClockwiseTheme {
        TaskCard(
            isPressed = true,
            onCardPress = {},
            task = Task(1, "Learning"),
            subTasks = listOf(
                SubTask(1, "Leetcode", taskId = 1),
                SubTask(2, "Android", taskId = 1)
            )
        )
    }
}
