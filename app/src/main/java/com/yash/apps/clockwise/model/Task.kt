package com.yash.apps.clockwise.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "parentId")
    val parentId: Int? = null,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false
)
