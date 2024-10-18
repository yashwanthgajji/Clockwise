package com.yash.apps.clockwise.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID

class OfflineTaskRepository(private val taskDao: TaskDao): TaskRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()
    override fun getTaskStream(id: UUID): Flow<Task> = taskDao.getTask(id)
    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun updateTask(task: Task) = taskDao.update(task)
    override suspend fun deleteTask(task: Task) = taskDao.delete(task)
}