package com.yash.apps.clockwise.data.repository

import com.yash.apps.clockwise.data.local.subtask.SubTaskDao
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import kotlinx.coroutines.flow.Flow

class OfflineSubTaskRepository(private val subTaskDao: SubTaskDao): SubTaskRepository {
    override suspend fun insertTask(subTask: SubTask) = subTaskDao.insert(subTask)
    override suspend fun updateTask(subTask: SubTask) = subTaskDao.update(subTask)
    override suspend fun deleteTask(subTask: SubTask) = subTaskDao.delete(subTask)
    override fun getAllSubTasksStream(): Flow<List<SubTask>> = subTaskDao.getAllSubTasks()
    override fun getSubTaskStream(id: Int): Flow<SubTask?> = subTaskDao.getSubTask(id)
    override fun getAllSubTasksOfTaskStream(taskId: Int): Flow<List<SubTask>> {
        return subTaskDao.getAllSubTasksOfTask(taskId)
    }
}