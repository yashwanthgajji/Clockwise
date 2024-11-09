package com.yash.apps.clockwise.presentation.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    viewModel: TimelineViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val timelineUiState by viewModel.timelineUiState.collectAsState()
    Scaffold(
        topBar = {
            TimelineScreenTopBar(title = "Timeline", scrollBehavior = scrollBehavior)
        }
    ) { innerPadding ->
        TimelineList(days = timelineUiState.days, modifier = modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimelineScreenTopBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Composable
private fun TimelineList(days: List<TimelineDay>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        days.forEach { day ->
            item {
                DaySectionHeader(dayString = day.date)
            }
            items(day.recordDetails) { recordDetail ->
                RecordItem(
                    taskName = recordDetail.taskName,
                    subTaskName = recordDetail.subTaskName ?: "",
                    totalDuration = recordDetail.duration,
                    onItemClick = { /*TODO*/ }
                )
            }
        }
    }
}

@Composable
private fun DaySectionHeader(dayString: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
//        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 2.dp)
        Text(
            text = dayString,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 4.dp)
    }
}

@Composable
private fun RecordItem(
    taskName: String,
    subTaskName: String,
    totalDuration: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onItemClick() }
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = taskName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = subTaskName,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onTertiaryContainer)
            ) {
                Text(
                    text = totalDuration,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inversePrimary)
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineListPreview() {
    val days = remember {
        listOf(
            TimelineDay(
                "Sunday, October 20th, 2024",
                listOf(
                    RecordDetails("Learning", "Leetcode", 1L, "02:31:15"),
                    RecordDetails("Gym", "Cardio", 1L, "00:50:17"),
                    RecordDetails("Course Work", "NLP", 1L, "14:20:02"),
                )
            ),
            TimelineDay(
                "Monday, October 14th, 2024",
                listOf(
                    RecordDetails("Gym", "Triceps", 1L, "01:10:00"),
                    RecordDetails("Learning", "Android Project", 1L, "00:31:15"),
                    RecordDetails("Gym", "Chest", date = 1L),
                )
            ),
            TimelineDay(
                "Saturday, October 12th, 2024",
                listOf(
                    RecordDetails("Course Work", "Database", 1L, "09:40:59"),
                    RecordDetails("Learning", date = 1L),
                )
            )
        )
    }
    ClockwiseTheme {
        TimelineList(days = days)
    }
}

@Preview(showBackground = true)
@Composable
private fun DaySectionHeaderPreview() {
    ClockwiseTheme {
        DaySectionHeader("Sunday, October 20th, 2024")
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemPreview() {
    ClockwiseTheme {
        RecordItem("Learning", "Leetcode", "20:18:59", onItemClick = { })
    }
}