package com.yash.apps.clockwise.data.repository

import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class OfflineTaskRepository(private val taskDao: TaskDao): TaskRepository {
    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun updateTask(task: Task) = taskDao.update(task)
    override suspend fun deleteTask(task: Task) = taskDao.delete(task)
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()
    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)
}