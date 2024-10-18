package com.yash.apps.clockwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yash.apps.clockwise.ui.ClockwiseApp
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockwiseTheme {
                ClockwiseApp()
            }
        }
    }
}