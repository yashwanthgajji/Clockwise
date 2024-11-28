package com.yash.apps.clockwise.util

import java.text.DateFormat
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

    fun getFirstDayOfCurrentMonth(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    fun areDatesInSameMonth(date1: Date, date2: Date): Boolean {
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()
        c1.time = date1
        c2.time = date2
        return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
    }

    fun gotoPreviousMonth(month: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = month
        cal.add(Calendar.MONTH, -1)
        return cal.time
    }

    fun gotoNextMonth(month: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = month
        cal.add(Calendar.MONTH, 1)
        return cal.time
    }
}