package com.yash.apps.clockwise.presentation.navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AppBottomNavigationPreview() {
    ClockwiseTheme {
        AppBottomNavigation(
            items = listOf(
                BottomNavigationItem(icon = R.drawable.timeline_icon, text = "Timeline"),
                BottomNavigationItem(icon = R.drawable.tasks_icon, text = "All Tasks"),
                BottomNavigationItem(icon = R.drawable.chart_icon, text = "Reports")
            ),
            selectedItem = 0,
            onItemClick = {}
        )
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)