package com.yash.apps.clockwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yash.apps.clockwise.R

val courierPrimeFontFamily = FontFamily(
    Font(R.font.courier_prime_regular, FontWeight.Normal),
    Font(R.font.courier_prime_bold, FontWeight.Bold),
    Font(R.font.courier_prime_italic, FontWeight.Light),
    Font(R.font.courier_prime_bold_italic, FontWeight.SemiBold)
)

val deliusFontFamily = FontFamily(
    Font(R.font.delius_regular, FontWeight.Normal)
)

val deliusSwashFontFamily = FontFamily(
    Font(R.font.delius_swash_caps_regular, FontWeight.Normal)
)

val sirachaFontFamily = FontFamily(
    Font(R.font.sriracha_regular, FontWeight.Normal)
)

val stylishFontFamily = FontFamily(
    Font(R.font.stylish_regular, FontWeight.Normal)
)

val rajdhaniFontFamily = FontFamily(
    Font(R.font.rajdhani_regular, FontWeight.Normal),
    Font(R.font.rajdhani_medium, FontWeight.SemiBold)
)

val chakraPetchFontFamily = FontFamily(
    Font(R.font.chakra_petch_regular, FontWeight.Normal),
    Font(R.font.chakra_petch_light, FontWeight.Light)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = sirachaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 54.sp,
        letterSpacing = 2.sp
    ), // Top App Bar
    headlineMedium = TextStyle(
        fontFamily = stylishFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        letterSpacing = 1.sp
    ), // Full Date display
    headlineSmall = TextStyle(
        fontFamily = courierPrimeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        lineHeight = 42.sp,
        letterSpacing = 1.sp
    ),
    displayLarge = TextStyle(
        fontFamily = deliusFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 1.sp
    ), // Task Names
    displayMedium = TextStyle(
        fontFamily = deliusSwashFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ), // Sub Task Names
    displaySmall = TextStyle(
        fontFamily = deliusSwashFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = courierPrimeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ), // Outlined buttons
    bodyMedium = TextStyle(
        fontFamily = courierPrimeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ), // Outlined buttons
    bodySmall = TextStyle(
        fontFamily = courierPrimeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ), // Duration
    labelMedium = TextStyle(
        fontFamily = rajdhaniFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ), // Labels with Icon
    labelSmall = TextStyle(
        fontFamily = rajdhaniFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.3.sp
    ) // Navigation Bar Labels
)