package com.yash.apps.clockwise.presentation.reports.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun MonthSelectorRow(
    modifier: Modifier = Modifier,
    month: String,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    isNextEnabled: Boolean
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onPrevClick) {
            Icon(
                painter = painterResource(id = R.drawable.prev_icon),
                contentDescription = "Go to previous month"
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = month,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(onClick = onNextClick, enabled = isNextEnabled) {
            Icon(
                painter = painterResource(id = R.drawable.next_icon),
                contentDescription = "Go to next month"
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MonthSelectorRowPreview() {
    ClockwiseTheme {
        MonthSelectorRow(
            month = "November",
            onPrevClick = { },
            onNextClick = { },
            isNextEnabled = true
        )
    }
}