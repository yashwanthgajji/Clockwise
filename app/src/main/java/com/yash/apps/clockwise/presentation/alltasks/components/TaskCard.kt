package com.yash.apps.clockwise.presentation.alltasks.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.common.TaskItem
import com.yash.apps.clockwise.presentation.timeline.components.NoSubTasksComponent
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onTaskClick: (Task) -> Unit,
    subTasks: List<SubTask> = emptyList(),
    onSubTaskClick: (SubTask) -> Unit = {}
) {
    var isPressed by rememberSaveable { mutableStateOf(false) }
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .clickable { isPressed = !isPressed }
                .clip(MaterialTheme.shapes.large),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            TaskItem(name = task.tName, onMoreClick = { onTaskClick(task) })
        }
        AnimatedVisibility(
            visible = isPressed,
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
            exit = fadeOut()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                if (subTasks.isEmpty()) {
                    NoSubTasksComponent(modifier = Modifier.fillMaxWidth())
                }
                repeat(subTasks.size) { index ->
                    TaskItem(
                        name = subTasks[index].sName,
                        onMoreClick = { onSubTaskClick(subTasks[index]) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskCardPreview() {
    ClockwiseTheme {
        TaskCard(
            task = Task(1, "Learning"),
            onTaskClick = {},
            subTasks = listOf(
                SubTask(1, "Leetcode", sTaskId = 1),
                SubTask(2, "Android", sTaskId = 1)
            ),
        )
    }
}
