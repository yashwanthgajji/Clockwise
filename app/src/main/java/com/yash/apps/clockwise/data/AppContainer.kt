package com.yash.apps.clockwise.data

import android.content.Context
import com.yash.apps.clockwise.data.repository.OfflineRecordRepository
import com.yash.apps.clockwise.domain.repository.RecordRepository
import com.yash.apps.clockwise.data.repository.OfflineSubTaskRepository
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import com.yash.apps.clockwise.data.repository.OfflineTaskRepository
import com.yash.apps.clockwise.domain.repository.TaskRepository

interface AppContainer {
    val taskRepository: TaskRepository
    val subTaskRepository: SubTaskRepository
    val recordRepository: RecordRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val taskRepository: TaskRepository by lazy {
        OfflineTaskRepository(TaskDatabase.getDatabase(context).taskDao())
    }
    override val subTaskRepository: SubTaskRepository by lazy {
        OfflineSubTaskRepository(TaskDatabase.getDatabase(context).subTaskDao())
    }
    override val recordRepository: RecordRepository by lazy {
        OfflineRecordRepository(TaskDatabase.getDatabase(context).recordDao())
    }
}