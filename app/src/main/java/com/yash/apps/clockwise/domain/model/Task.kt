package com.yash.apps.clockwise.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tId")
    val tId: Int = 0,
    @ColumnInfo(name = "tName")
    val tName: String,
    @ColumnInfo(name = "tDuration")
    val tDuration: Long? = null,
    @ColumnInfo(name = "tIsCompleted")
    val tIsCompleted: Boolean = false
)
