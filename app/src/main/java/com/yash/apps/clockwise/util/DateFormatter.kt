package com.yash.apps.clockwise.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
object DateFormatter {
    fun formatDate(date: Date, format: String): String {
        return SimpleDateFormat(format).format(date)
    }

    @SuppressLint("DefaultLocale")
    fun formatDuration(duration: Long, format: String): String {
        val durationInSeconds = duration / 1000
        val seconds = durationInSeconds % 60
        val durationInMinutes = durationInSeconds / 60
        val minutes = durationInMinutes % 60
        val durationInHours = durationInMinutes / 60
        return String.format("%02d:%02d:%02d", durationInHours, minutes, seconds)
    }
}