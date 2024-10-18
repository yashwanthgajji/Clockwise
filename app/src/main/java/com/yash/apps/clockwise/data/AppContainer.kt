package com.yash.apps.clockwise.data

import android.content.Context
import com.yash.apps.clockwise.data.record.OfflineRecordRepository
import com.yash.apps.clockwise.data.record.RecordRepository
import com.yash.apps.clockwise.data.task.OfflineTaskRepository
import com.yash.apps.clockwise.data.task.TaskRepository

interface AppContainer {
    val taskRepository: TaskRepository
    val recordRepository: RecordRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val taskRepository: TaskRepository by lazy {
        OfflineTaskRepository(TaskDatabase.getDatabase(context).taskDao())
    }
    override val recordRepository: RecordRepository by lazy {
        OfflineRecordRepository(TaskDatabase.getDatabase(context).recordDao())
    }
}