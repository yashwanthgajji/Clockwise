package com.yash.apps.clockwise.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yash.apps.clockwise.R
import com.yash.apps.clockwise.ui.theme.ClockwiseTheme

@Composable
fun IconWithLabel(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconDescription: String? = null,
    label: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(modifier = Modifier.size(64.dp), painter = painterResource(id = icon), contentDescription = iconDescription)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = label, style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center, )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun IconWithLabelPreview() {
    ClockwiseTheme {
        IconWithLabel(icon = R.drawable.empty_folder_icon, label = "Empty Folder")
    }
}