package com.yash.apps.clockwise.data

import android.content.Context

interface AppContainer {
    val taskRepository: TaskRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val taskRepository: TaskRepository by lazy {
        OfflineTaskRepository(TaskDatabase.getDatabase(context).taskDao())
    }
}