package com.yash.apps.clockwise.presentation.allTasks.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun NewTaskFabComponent(modifier: Modifier = Modifier, onSave: (String) -> Unit) {
    var isInsertForm = rememberSaveable { mutableStateOf(false) }
    var taskName = rememberSaveable { mutableStateOf("") }
    AnimatedContent(
        modifier = modifier,
        targetState = isInsertForm.value,
        label = "",
        transitionSpec = { fadeIn() togetherWith fadeOut() }
    ) { flag ->
        if (flag) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = taskName.value,
                    onValueChange = { taskName.value = it },
                    placeholder = { Text(text = "Enter Task Name") },
                    singleLine = true
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { isInsertForm.value = false }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                    Button(onClick = {
                        onSave(taskName.value)
                        taskName.value = ""
                        isInsertForm.value = false
                    }) {
                        Text(text = "Save")
                    }
                }
            }
        } else {
            Button(onClick = { isInsertForm.value = true }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.add_task_icon),
                        contentDescription = null,
                    )
                    Text(text = "New Task")
                }
            }

        }
    }
}

@Preview
@Composable
private fun NewTaskFabButton() {
    ClockwiseTheme {
        NewTaskFabComponent(onSave = {})
    }
}