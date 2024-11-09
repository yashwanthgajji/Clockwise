package com.yash.apps.clockwise.presentation.taskdetails.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun TaskDetailTabRow(modifier: Modifier = Modifier, selectedTab: Int, onTabSelected: (Int) -> Unit) {
    var tabs = listOf("Records", "Sub Tasks")
    TabRow(
        modifier = modifier.clip(MaterialTheme.shapes.small),
        selectedTabIndex = selectedTab,
        indicator = { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        tabs.forEachIndexed { index, tabTitle ->
            Tab(
                modifier = Modifier.padding(vertical = 8.dp),
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = MaterialTheme.colorScheme.secondary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ) {
                Text(text = tabTitle)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskDetailTabRowPreview() {
    ClockwiseTheme {
        TaskDetailTabRow(selectedTab = 0, onTabSelected = {})
    }
}