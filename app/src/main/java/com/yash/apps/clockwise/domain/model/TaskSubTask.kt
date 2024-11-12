package com.yash.apps.clockwise.domain.model

data class TaskSubTask(
    val tId: Int,
    val tName: String,
    val tDuration: Long? = null,
    val tIsCompleted: Boolean = false,
    val sId: Int? = null,
    val sName: String? = null,
    val sDuration: Long? = null,
    val sIsCompleted: Boolean = false,
)
