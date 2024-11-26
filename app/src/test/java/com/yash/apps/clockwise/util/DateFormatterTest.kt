package com.yash.apps.clockwise.util

import com.yash.apps.clockwise.util.Constants.DATE_FORMAT
import com.yash.apps.clockwise.util.Constants.DURATION_FORMAT
import com.yash.apps.clockwise.util.Constants.FULL_DATE_FORMAT
import com.yash.apps.clockwise.util.Constants.ONLY_MONTH_YEAR_FORMAT
import com.yash.apps.clockwise.util.Constants.REPORT_SHARE_IMAGE_NAME
import com.yash.apps.clockwise.util.Constants.SELECTOR_DATE_FORMAT
import com.yash.apps.clockwise.util.Constants.TIME_FORMAT
import com.yash.apps.clockwise.util.DateFormatter.formatDate
import com.yash.apps.clockwise.util.DateFormatter.formatDuration
import org.junit.Assert.assertEquals

import org.junit.Test
import java.util.Date

class DateFormatterTest {

    private val dateToUse = Date(1732590712294L) //Tue Nov 26 03:11:52 UTC 2024

    @Test
    fun formatDate_timeFormat() {
        val expected = "10:11 PM"
        val actual = formatDate(dateToUse, TIME_FORMAT)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDate_selectorDateFormat() {
        val expected = "Mon\n" + "11/25"
        val actual = formatDate(dateToUse, SELECTOR_DATE_FORMAT)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDate_dateFormat() {
        val expected = "11/25/2024"
        val actual = formatDate(dateToUse, DATE_FORMAT)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDate_fullDateFormat() {
        val expected = "Monday, November 25, 2024"
        val actual = formatDate(dateToUse, FULL_DATE_FORMAT)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDate_onlyMonthYearFormat() {
        val expected = "November 2024"
        val actual = formatDate(dateToUse, ONLY_MONTH_YEAR_FORMAT)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDate_reportShareImageNameFormat() {
        val expected = "November 25, 2024"
        val actual = formatDate(dateToUse, REPORT_SHARE_IMAGE_NAME)
        assertEquals(expected, actual)
    }

    @Test
    fun formatDuration_durationFormat() {
        val durationToUse = 13527000L
        val expected = "03:45:27"
        val actual = formatDuration(durationToUse, DURATION_FORMAT)
        assertEquals(expected, actual)
    }
}