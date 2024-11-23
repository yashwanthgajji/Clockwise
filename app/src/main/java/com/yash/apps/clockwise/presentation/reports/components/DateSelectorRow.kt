package com.yash.apps.clockwise.presentation.reports.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun DateSelectorRow(
    modifier: Modifier = Modifier,
    dates: List<Date>,
    dateSelected: Date,
    todayDate: Date,
    onDateSelected: (Date) -> Unit,
    lazyListState: LazyListState
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onBackground),
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(dates) { date ->
            DateSelectorRowItem(
                date = date,
                enabled = date <= todayDate,
                selected = date == dateSelected,
                onSelected = onDateSelected
            )
        }
    }
}