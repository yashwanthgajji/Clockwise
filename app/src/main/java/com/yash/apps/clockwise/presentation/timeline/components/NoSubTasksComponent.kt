package com.yash.apps.clockwise.presentation.timeline.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun NoSubTasksComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.empty_box_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "No sub tasks found")
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NoSubTasksComponentPreview() {
    ClockwiseTheme {
        NoSubTasksComponent(modifier = Modifier.fillMaxWidth())
    }
}