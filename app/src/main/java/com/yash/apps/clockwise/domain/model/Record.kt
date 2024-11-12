package com.yash.apps.clockwise.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rId")
    val rId: Int = 0,
    @ColumnInfo(name = "rDate")
    val rDate: Date,
    @ColumnInfo(name = "rStartTime")
    val rStartTime: Date,
    @ColumnInfo(name = "rEndTime")
    val rEndTime: Date,
    @ColumnInfo(name = "rDuration")
    val rDuration: Long,
    @ColumnInfo(name = "rTaskId")
    val rTaskId: Int,
    @ColumnInfo(name = "rSubTaskId")
    val rSubTaskId: Int? = null,
)