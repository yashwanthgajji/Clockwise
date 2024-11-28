package com.yash.apps.clockwise.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "subTasks")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sId")
    val sId: Int = 0,
    @ColumnInfo(name = "sName")
    val sName: String,
    @ColumnInfo(name = "sDuration")
    val sDuration: Long? = null,
    @ColumnInfo(name = "sIsCompleted")
    val sIsCompleted: Boolean = false,
    @ColumnInfo(name = "sTaskId")
    val sTaskId: Int
): Parcelable