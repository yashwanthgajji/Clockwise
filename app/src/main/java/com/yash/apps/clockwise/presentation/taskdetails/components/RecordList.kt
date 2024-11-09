package com.yash.apps.clockwise.presentation.taskdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.presentation.common.IconWithLabel
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun RecordList(modifier: Modifier = Modifier, records: List<Record> = emptyList()) {
    if (records.isEmpty()) {
        IconWithLabel(
            modifier = modifier
                .fillMaxSize(),
            icon = R.drawable.hour_glass,
            label = "Start working on task"
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {

        }
    }
}

@Preview
@Composable
private fun RecordListPreview() {
    ClockwiseTheme {
        RecordList()
    }
}