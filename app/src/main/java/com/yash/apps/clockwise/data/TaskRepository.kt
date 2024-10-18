package com.yash.apps.clockwise.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TaskRepository {
    fun getAllTasksStream(): Flow<List<Task>>
    fun getTaskStream(id:UUID): Flow<Task>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}