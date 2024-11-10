package com.yash.apps.clockwise.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subTasks")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sId")
    val sId: Int,
    @ColumnInfo(name = "sName")
    val sName: String,
    @ColumnInfo(name = "sDuration")
    val sDuration: Long? = null,
    @ColumnInfo(name = "sIsCompleted")
    val sIsCompleted: Boolean = false,
    @ColumnInfo(name = "sTaskId")
    val sTaskId: Int
)