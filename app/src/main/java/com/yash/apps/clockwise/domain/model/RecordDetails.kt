package com.yash.apps.clockwise.domain.model

import com.yash.apps.clockwise.util.Constants.FULL_DATE_FORMAT
import com.yash.apps.clockwise.util.Constants.TIME_FORMAT
import com.yash.apps.clockwise.util.DateFormatter.formatDate
import java.util.Date

data class RecordDetails(
    val rId: Int,
    val rDate: Date,
    val rStartTime: Date,
    val rEndTime: Date,
    val rDuration: Long,
    val tId: Int,
    val tName: String,
    val sId: Int? = null,
    val sName: String? = null
) {
    fun getStartTimeInString(): String {
        return formatDate(rStartTime, TIME_FORMAT)
    }

    fun getEndTimeInString(): String {
        return formatDate(rEndTime, TIME_FORMAT)
    }

    fun getDurationInString(): String {
        return "00:00:00"
    }

    fun getDateInString(): String {
        return formatDate(rDate, FULL_DATE_FORMAT)
    }
}
