package com.yash.apps.clockwise.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
object DateFormatter {
    fun formatDate(date: Date, format: String): String {
        return SimpleDateFormat(format).format(date)
    }
}