package com.yash.apps.clockwise.presentation.taskdetails

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.presentation.taskdetails.components.RecordList
import com.yash.apps.clockwise.presentation.taskdetails.components.TaskDetailTabRow
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun TaskDetailScreen(modifier: Modifier = Modifier) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Task Name", style = MaterialTheme.typography.headlineLarge)
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
            when(tab) {
                0 -> { RecordList() }
                2 -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskDetailScreenPreview() {
    ClockwiseTheme {
        TaskDetailScreen()
    }
}