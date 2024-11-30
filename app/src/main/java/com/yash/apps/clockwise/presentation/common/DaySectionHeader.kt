package com.yash.apps.clockwise.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun DaySectionHeader(dayString: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
//        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 2.dp)
        Text(
            text = dayString,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer, thickness = 4.dp)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DaySectionHeaderPreview() {
    ClockwiseTheme {
        DaySectionHeader("Sunday, October 20th, 2024")
    }
}