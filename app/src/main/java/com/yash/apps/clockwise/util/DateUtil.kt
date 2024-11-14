package com.yash.apps.clockwise.util

import java.util.Calendar
import java.util.Date

object DateUtil {
    fun getCurrentDateWithMidnightTime(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }
}