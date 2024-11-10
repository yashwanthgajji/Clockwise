package com.yash.apps.clockwise.presentation.taskdetails.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme
import java.util.Date

@Composable
fun RecordList(modifier: Modifier = Modifier, recordDetails: List<RecordDetails>) {
    if (recordDetails.isEmpty()) {
        IconWithLabel(
            modifier = modifier,
            icon = R.drawable.hour_glass,
            label = "Start working on task"
        )
    } else {
        LazyColumn(
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            items(recordDetails) { recordDetail ->
                RecordListItem(
                    recordDetails = recordDetail
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListPreview() {
    val records = listOf(
        RecordDetails(
            rId = 1,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = Date().time,
            tId = 1,
            tName = "Studying",
            sId = 2,
            sName = "Kotlin Programming"
        ),
        RecordDetails(
            rId = 2,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = Date().time,
            tId = 1,
            tName = "Studying"
        )
    )
    ClockwiseTheme {
        RecordList(recordDetails = records)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListEmptyPreview() {
    ClockwiseTheme {
        RecordList(recordDetails = emptyList())
    }
}