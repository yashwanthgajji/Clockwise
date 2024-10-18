package com.yash.apps.clockwise.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasksStream(): Flow<List<Task>>
    fun getTaskStream(id:Int): Flow<Task>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}