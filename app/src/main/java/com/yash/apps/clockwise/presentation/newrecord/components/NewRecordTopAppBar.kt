package com.yash.apps.clockwise.presentation.newrecord.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecordTopAppBar(modifier: Modifier = Modifier, onBackPress: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "New Record", style = MaterialTheme.typography.headlineMedium)},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Go Back"
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NewRecordTopAppBarPreview() {
    ClockwiseTheme {
        NewRecordTopAppBar(onBackPress = {})
    }
}