package com.yash.apps.clockwise.ui.home

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.model.Task
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold() { innerPadding ->
        TimelineList(days = listOf(), modifier = modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(title: String, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Preview
@Composable
fun HomeScreenTopBarPreview() {
    ClockwiseTheme {
        HomeScreenTopBar(title = "TimeLine")
    }
}

@Composable
fun TimelineList(days: List<Section>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        days.forEach { day ->
            item {
                DaySectionHeader(dayString = day.title)
            }
            items(day.tasks) { task ->
                TaskItem(
                    taskName = task.name,
                    subTaskName = task.name,
                    totalDuration = task.name,
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun DaySectionHeader(dayString: String, modifier: Modifier = Modifier) {
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
fun TaskItem(
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
fun TimelineListPreview() {
    val days = remember {
        listOf(
            Section(
                "Sunday, October 20th, 2024",
                listOf(
                    Task(1, "Learning"),
                    Task(1, "Certification"),
                    Task(1, "Project")
                )
            ),
            Section(
                "Monday, October 14th, 2024",
                listOf(
                    Task(1, "Gym"),
                    Task(1, "Exercise"),
                    Task(1, "Walking")
                )
            ),
            Section(
                "Saturday, October 12th, 2024",
                listOf(
                    Task(1, "Course Work"),
                    Task(1, "NLP"),
                    Task(1, "Database")
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
fun DaySectionHeaderPreview() {
    ClockwiseTheme {
        DaySectionHeader("Sunday, October 20th, 2024")
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    ClockwiseTheme {
        TaskItem("Learning", "Leetcode", "20:18:59", onItemClick = { })
    }
}