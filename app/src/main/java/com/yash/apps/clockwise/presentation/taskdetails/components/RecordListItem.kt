package com.yash.apps.clockwise.presentation.taskdetails.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun RecordListItem(
    modifier: Modifier = Modifier,
    startTime: String,
    endTime: String,
    duration: String,
    subTaskName: String? = null
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = startTime)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "-")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = endTime)
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = duration,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (!subTaskName.isNullOrEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = subTaskName)
            }
        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListItemPreview() {
    ClockwiseTheme {
        RecordListItem(startTime = "03:45 pm", endTime = "04:50 pm", duration = "00:01:05", subTaskName = "Library")
    }
}