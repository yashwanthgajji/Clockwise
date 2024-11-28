package com.yash.apps.clockwise.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.RecordListItemValue
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun RecordList(
    modifier: Modifier = Modifier,
    recordListItemValues: List<RecordListItemValue>,
    isSubTaskList: Boolean = false
) {
    if (recordListItemValues.isEmpty()) {
        IconWithLabel(
            modifier = modifier,
            icon = R.drawable.hour_glass,
            label = "Start working on task"
        )
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(recordListItemValues) { recordListItemValue ->
                DaySectionHeader(dayString = recordListItemValue.date)
                repeat(recordListItemValue.recordDetails.size) { index ->
                    val recordDetails = recordListItemValue.recordDetails[index]
                    RecordListItem(
                        startTime = recordDetails.getStartTimeInString(),
                        endTime = recordDetails.getEndTimeInString(),
                        duration = recordDetails.getDurationInString(),
                        subTaskName = if (!isSubTaskList) recordDetails.sName else null
                    )
                    if (index != recordListItemValue.recordDetails.size - 1) {
                        HorizontalDivider()
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 4.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListEmptyPreview() {
    ClockwiseTheme {
        RecordList(recordListItemValues = emptyList())
    }
}