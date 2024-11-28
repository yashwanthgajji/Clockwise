package com.yash.apps.clockwise.util

import com.yash.apps.clockwise.util.DateUtil.areDatesInSameMonth
import com.yash.apps.clockwise.util.DateUtil.getCurrentDateWithMidnightTime
import com.yash.apps.clockwise.util.DateUtil.getFirstDayOfCurrentMonth
import com.yash.apps.clockwise.util.DateUtil.gotoNextMonth
import com.yash.apps.clockwise.util.DateUtil.gotoPreviousMonth
import org.junit.Assert.*

import org.junit.Test
import java.util.Calendar
import java.util.Date

class DateUtilTest {

    @Test
    fun getCurrentDateWithMidnightTime_returnsTodayMidnightTime() {
        val actual = getCurrentDateWithMidnightTime()
        val expected = Calendar.getInstance()
        expected.set(Calendar.HOUR_OF_DAY, 0)
        expected.set(Calendar.MINUTE, 0)
        expected.set(Calendar.SECOND, 0)
        expected.set(Calendar.MILLISECOND, 0)
        assertEquals(expected.time, actual)
    }

    @Test
    fun getFirstDayOfCurrentMonth_returnFirstDayOfMonth() {
        val expected = Calendar.getInstance()
        expected.set(Calendar.DAY_OF_MONTH, 1)
        expected.set(Calendar.HOUR_OF_DAY, 0)
        expected.set(Calendar.MINUTE, 0)
        expected.set(Calendar.SECOND, 0)
        expected.set(Calendar.MILLISECOND, 0)
        val actual = getFirstDayOfCurrentMonth()
        assertEquals(expected.time, actual)
    }

    @Test
    fun areDatesInSameMonth_sameMonthDates_returnsTrue() {
        val date1 = Date(1732590712294L) //Tue Nov 26 03:11:52 UTC 2024
        val date2 = Date(1731726712294L)
        assertTrue(areDatesInSameMonth(date1, date2))
    }

    @Test
    fun areDatesInSameMonth_differentMonthDates_returnsFalse() {
        val date1 = Date(1732590712294L)
        val date2 = Date(1736997112294L)
        assertFalse(areDatesInSameMonth(date1, date2))
    }

    @Test
    fun areDatesInSameMonth_differentYearDates_returnsFalse() {
        val date1 = Date(1732590712294L)
        val date2 = Date(1764126712294L)
        assertFalse(areDatesInSameMonth(date1, date2))
    }

    @Test
    fun areDatesInSameMonth_differentYearAndMonthDates_returnsFalse() {
        val date1 = Date(1732590712294L)
        val date2 = Date(1756174312294L)
        assertFalse(areDatesInSameMonth(date1, date2))
    }

    @Test
    fun gotoPreviousMonth_returnsPreviousMonthDate() {
        val originalDate = Date(1732590712294L)
        val expectedPreviousMonthDate = Date(1729908712294L)
        val actualPreviousMonthDate = gotoPreviousMonth(originalDate)
        assertEquals(expectedPreviousMonthDate, actualPreviousMonthDate)
    }

    @Test
    fun gotoNextMonth() {
        val originalDate = Date(1732590712294L)
        val expectedNextMonthDate = Date(1735182712294)
        val actualNextMonthDate = gotoNextMonth(originalDate)
        assertEquals(expectedNextMonthDate, actualNextMonthDate)
    }
}