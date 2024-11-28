package com.yash.apps.clockwise.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(16.dp),
    small = RoundedCornerShape(24.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
    large = RoundedCornerShape(topStart = 24.dp, topEnd = 8.dp, bottomStart = 8.dp, bottomEnd = 24.dp)
)
