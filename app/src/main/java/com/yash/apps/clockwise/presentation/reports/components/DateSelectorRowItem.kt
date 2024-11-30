package com.yash.apps.clockwise.presentation.reports.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme
import com.yash.apps.clockwise.util.Constants.SELECTOR_DATE_FORMAT
import com.yash.apps.clockwise.util.DateFormatter
import java.util.Date

@Composable
fun DateSelectorRowItem(
    modifier: Modifier = Modifier,
    date: Date,
    enabled: Boolean,
    selected: Boolean,
    onSelected: (Date) -> Unit
) {
    val backgroundColor = when {
        !enabled -> MaterialTheme.colorScheme.background
        selected -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.secondaryContainer
    }
    val textColor = when {
        !enabled -> MaterialTheme.colorScheme.onBackground
        selected -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.onSecondaryContainer
    }
    Row(
        modifier = modifier
            .width(72.dp)
            .height(56.dp)
            .background(backgroundColor)
            .clickable { if (enabled) onSelected(date) },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VerticalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
        Text(
            modifier = Modifier.weight(1f),
            text = DateFormatter.formatDate(date, SELECTOR_DATE_FORMAT),
            maxLines = 2,
            textAlign = TextAlign.Center,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
        VerticalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DateSelectorRowItemView(modifier: Modifier = Modifier) {
    ClockwiseTheme {
        DateSelectorRowItem(date = Date(), enabled = true, selected = false, onSelected = {})
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DateSelectorRowItemDisabledView(modifier: Modifier = Modifier) {
    ClockwiseTheme {
        DateSelectorRowItem(date = Date(), enabled = false, selected = false, onSelected = {})
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DateSelectorRowItemSelectedView(modifier: Modifier = Modifier) {
    ClockwiseTheme {
        DateSelectorRowItem(date = Date(), enabled = true, selected = true, onSelected = {})
    }
}