package com.yash.apps.clockwise.presentation.timeline

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.presentation.timeline.components.TimelineList
import com.yash.apps.clockwise.presentation.timeline.components.TimelineScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    timelineUiState: TimelineUiState,
    bottomBarContent: @Composable () -> Unit,
    isActiveSession: Boolean = false,
    activeSessionComponent: @Composable () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TimelineScreenTopBar(title = "Timeline", scrollBehavior = scrollBehavior)
        },
        bottomBar = bottomBarContent
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            AnimatedVisibility(visible = isActiveSession) {
                activeSessionComponent()
                Spacer(modifier = Modifier.height(8.dp))
            }
            TimelineList(
                days = timelineUiState.days
            )
        }
    }
}