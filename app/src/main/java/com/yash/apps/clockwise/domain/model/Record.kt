package com.yash.apps.clockwise.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recordId")
    val recordId: Int,
    @ColumnInfo(name = "taskId")
    val taskId: Int,
    @ColumnInfo(name = "subTaskId")
    val subTaskId: Int? = null,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "startTime")
    val startTime: Long,
    @ColumnInfo(name = "endTime")
    val endTime: Long,
    @ColumnInfo(name = "duration")
    val duration: String
)