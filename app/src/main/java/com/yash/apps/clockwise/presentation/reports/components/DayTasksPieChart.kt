package com.yash.apps.clockwise.presentation.reports.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
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
import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme
import com.yash.apps.clockwise.util.ColorUtil.colors
import com.yash.apps.clockwise.util.ColorUtil.emptyColor
import com.yash.apps.clockwise.util.Constants.DURATION_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie

@Composable
fun DayTasksPieChart(
    modifier: Modifier = Modifier,
    reportDataList: List<ReportDataValue>
) {
    var pieDataList by rememberSaveable { mutableStateOf(emptyList<Pie>()) }
    if (reportDataList.isNotEmpty()) {
        val totalDuration = reportDataList.sumOf { it.taskDuration }.toDouble()
        pieDataList = reportDataList.mapIndexed { index, reportDataValue ->
            Pie(
                label = reportDataValue.taskName,
                data = (reportDataValue.taskDuration.toDouble() / totalDuration),
                color = colors[index % colors.size],
            )
        }
    } else {
        pieDataList = listOf(
            Pie(
                label = "No Data",
                data = 1.0,
                color = emptyColor
            )
        )
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (reportDataList.isEmpty()) {
            item {
                IconWithLabel(
                    icon = R.drawable.clock_icon,
                    label = "You haven't worked on any task today"
                )
            }
        } else {
            item {
                Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
                    PieChart(
                        modifier = Modifier.size(160.dp),
                        data = pieDataList,
                        onPieClick = {
                            val pieIndex = pieDataList.indexOf(it)
                            pieDataList = pieDataList.mapIndexed { mapIndex, pie ->
                                pie.copy(selected = pieIndex == mapIndex)
                            }
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
                        style = Pie.Style.Stroke(width = 36.dp)
                    )
                }
            }
            itemsIndexed(reportDataList) { index, reportData ->
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
                        text = reportData.taskName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = DateFormatter.formatDuration(
                            reportData.taskDuration,
                            DURATION_FORMAT
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllTasksMultiLineChartPreview() {
    val reportDataList = listOf(
        ReportDataValue(
            taskName = "Gym",
            taskDuration = 1800000L
        ),
        ReportDataValue(
            taskName = "Development",
            taskDuration = 7200000L
        ),
        ReportDataValue(
            taskName = "Reading",
            taskDuration = 3600000L
        )
    )
    ClockwiseTheme {
        DayTasksPieChart(reportDataList = reportDataList)
    }
}

@Preview(showBackground = true)
@Composable
private fun AllTasksMultiLineChartEmptyPreview() {
    ClockwiseTheme {
        DayTasksPieChart(reportDataList = emptyList())
    }
}