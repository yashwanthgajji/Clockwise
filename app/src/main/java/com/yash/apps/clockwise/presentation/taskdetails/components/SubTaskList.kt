package com.yash.apps.clockwise.presentation.taskdetails.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.presentation.common.TaskItem
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun SubTaskList(
    modifier: Modifier = Modifier,
    subTasks: List<SubTask>,
    onSubTaskClick: (SubTask) -> Unit
) {
    AnimatedVisibility (subTasks.isEmpty(), enter = fadeIn(), exit = fadeOut()) {
        IconWithLabel(
            modifier = modifier.fillMaxSize(),
            icon = R.drawable.empty_folder_icon,
            label = "No sub tasks found"
        )
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(subTasks) { subTask ->
            TaskItem(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                name = subTask.sName,
                onMoreClick = { onSubTaskClick(subTask) }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SubTaskListPreview() {
    val subTasks = listOf(
        SubTask(sName = "Complete Project", sTaskId = 1),
        SubTask(sName = "Create play store profile", sTaskId = 1),
        SubTask(sName = "Upload to git", sTaskId = 1),
        SubTask(sName = "Create APK", sTaskId = 1),
        SubTask(sName = "Deploy in play store", sTaskId = 1)
    )
    ClockwiseTheme {
        SubTaskList(
            subTasks = subTasks,
            onSubTaskClick = {}
        )
    }
}