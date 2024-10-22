package com.yash.apps.clockwise.data.subtask

import com.yash.apps.clockwise.model.SubTask
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
    override fun getSubTaskNameById(id: Int): String = subTaskDao.getSubTaskName(id)
}