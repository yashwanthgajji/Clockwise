package com.yash.apps.clockwise.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yash.apps.clockwise.presentation.navigator.AppNavigator

@Composable
fun ClockwiseApp(modifier: Modifier = Modifier) {
    AppNavigator(modifier = modifier.fillMaxSize())
}