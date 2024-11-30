package com.yash.apps.clockwise.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yash.apps.clockwise.R

@Composable
fun TaskItem(
    name: String,
    modifier: Modifier = Modifier,
    onMoreClick: () -> Unit,
    onPlayClick: () -> Unit,
    isPlayEnabled: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                lineHeight = 28.sp
            )
        )
        IconButton(
            onClick = onPlayClick,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            enabled = isPlayEnabled
        ) {
            Icon(
                painter = painterResource(id = R.drawable.play_icon),
                contentDescription = "start Task $name",
                modifier = Modifier.size(20.dp)
            )
        }
        IconButton(
            onClick = onMoreClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "start Task $name",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
