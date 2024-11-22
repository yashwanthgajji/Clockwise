package com.yash.apps.clockwise.presentation.reports.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme
import com.yash.apps.clockwise.util.ColorUtil.colors
import com.yash.apps.clockwise.util.ColorUtil.emptyColor
import com.yash.apps.clockwise.util.Constants.DURATION_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie

@Composable
fun DayTasksPieChart(tasks: List<Task>, modifier: Modifier = Modifier) {
    var data by rememberSaveable { mutableStateOf(emptyList<Pie>()) }
    if (tasks.isEmpty()) {
        data = listOf(
            Pie(
                label = "Not worked today",
                data = 1.0,
                color = emptyColor
            )
        )
    } else {
        var totalDuration = 0F
        for (task in tasks) {
            task.tDuration?.let {
                totalDuration += it
            }
        }
        data = tasks.mapIndexed { index, task ->
            task.tDuration?.let {
                Pie(
                    label = task.tName,
                    data = (it.toFloat() / totalDuration).toDouble(),
                    color = colors[index],
                )
            }
        }.filterNotNull()
    }
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PieChart(
            modifier = Modifier.size(120.dp),
            data = data,
            onPieClick = {
                val pieIndex = data.indexOf(it)
                data =
                    data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
            },
            selectedScale = 1.2f,
            scaleAnimEnterSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            colorAnimEnterSpec = tween(300),
            colorAnimExitSpec = tween(300),
            scaleAnimExitSpec = tween(300),
            spaceDegreeAnimExitSpec = tween(300),
            spaceDegree = 2f,
            selectedPaddingDegree = 4f,
            style = Pie.Style.Stroke(width = 24.dp)
        )
        if (tasks.isEmpty()) {
            IconWithLabel(
                modifier = Modifier
                    .height(120.dp)
                    .weight(1f),
                icon = R.drawable.task_box_icon,
                label = "You haven't worked on any task today"
            )
        } else {
            LazyColumn(
                modifier = Modifier.height(120.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                itemsIndexed(tasks) { index, task ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(color = colors[index])
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = task.tName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = DateFormatter.formatDuration(
                                task.tDuration ?: 0L,
                                DURATION_FORMAT
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllTasksMultiLineChartPreview() {
    val tasks = listOf(
        Task(
            tName = "Gym",
            tDuration = 1800L
        ),
        Task(
            tName = "Development",
            tDuration = 7200L
        ),
        Task(
            tName = "Reading",
            tDuration = 3600L
        ),
    )
    ClockwiseTheme {
        DayTasksPieChart(tasks = tasks)
    }
}

@Preview(showBackground = true)
@Composable
private fun AllTasksMultiLineChartEmptyPreview() {
    ClockwiseTheme {
        DayTasksPieChart(tasks = emptyList())
    }
}