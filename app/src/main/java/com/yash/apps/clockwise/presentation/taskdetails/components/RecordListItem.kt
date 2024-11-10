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
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme
import java.util.Date

@Composable
fun RecordListItem(
    modifier: Modifier = Modifier,
    recordDetails: RecordDetails
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
            Text(text = recordDetails.getStartTimeInString())
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "-")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = recordDetails.getEndTimeInString())
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = recordDetails.getDurationInString(),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        recordDetails.sName?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = recordDetails.sName)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListItemPreview() {
    ClockwiseTheme {
        RecordListItem(
            recordDetails = RecordDetails(
                rId = 1,
                rDate = Date(),
                rStartTime = Date(Date().time - (70 * 60 * 1000)),
                rEndTime = Date(),
                rDuration = (70 * 60 * 1000),
                tId = 1,
                tName = "Studying",
                sId = 2,
                sName = "Kotlin Programming"
            )
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun RecordListItemWithoutSubTaskPreview() {
    ClockwiseTheme {
        RecordListItem(
            recordDetails = RecordDetails(
                rId = 1,
                rDate = Date(),
                rStartTime = Date(Date().time - (135 * 60 * 1000)),
                rEndTime = Date(),
                rDuration = (70 * 60 * 1000) + (25 * 1000),
                tId = 1,
                tName = "Studying"
            )
        )
    }
}