package com.yash.apps.clockwise.presentation.reports.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Date

@Composable
fun DateSelectorRow(
    modifier: Modifier = Modifier,
    dates: List<Date>,
    dateSelected: Date,
    todayDate: Date,
    onDateSelected: (Date) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = rememberLazyListState(
            initialFirstVisibleItemIndex = dates.indexOf(todayDate) - 4
        ),
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