package com.yash.apps.clockwise.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(24.dp),
    small = RoundedCornerShape(50.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
    large = RoundedCornerShape(topStart = 24.dp, bottomEnd = 24.dp)
)
