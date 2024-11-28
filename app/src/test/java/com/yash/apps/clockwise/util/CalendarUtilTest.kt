package com.yash.apps.clockwise.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class CalendarUtilTest {

    @Test
    fun getCurrentDate_returnsTodayDate() {
        val actual = CalendarUtil.getCurrentDate()
        val expected = Calendar.getInstance()
        expected.set(Calendar.HOUR_OF_DAY, 0)
        expected.set(Calendar.MINUTE, 0)
        expected.set(Calendar.SECOND, 0)
        expected.set(Calendar.MILLISECOND, 0)
        assertEquals(expected, actual)
    }

    @Test
    fun getCurrentMonth_returnsFirstDayOfMonth() {
        val actual = CalendarUtil.getCurrentMonth()
        val expected = Calendar.getInstance()
        expected.set(Calendar.DAY_OF_MONTH, 1)
        expected.set(Calendar.HOUR_OF_DAY, 0)
        expected.set(Calendar.MINUTE, 0)
        expected.set(Calendar.SECOND, 0)
        expected.set(Calendar.MILLISECOND, 0)
        assertEquals(expected, actual)
    }
}