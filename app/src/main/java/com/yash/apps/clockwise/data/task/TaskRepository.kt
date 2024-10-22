package com.yash.apps.clockwise.data.task

import com.yash.apps.clockwise.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getAllTasksStream(): Flow<List<Task>>
    fun getTaskStream(id:Int): Flow<Task?>
    fun getTaskNameById(id: Int): String
}