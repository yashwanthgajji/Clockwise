package com.yash.apps.clockwise.presentation.timeline

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.presentation.timeline.components.TimelineList
import com.yash.apps.clockwise.presentation.timeline.components.TimelineScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    timelineUiState: TimelineUiState,
    bottomBarContent: @Composable () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TimelineScreenTopBar(title = "Timeline", scrollBehavior = scrollBehavior)
        },
        bottomBar = bottomBarContent
    ) { innerPadding ->
        TimelineList(days = timelineUiState.days, modifier = modifier.padding(innerPadding))
    }
}