package com.yash.apps.clockwise.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subTasks")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "duration")
    val duration: String? = null,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false,
    @ColumnInfo(name = "taskId")
    val taskId: Int
)