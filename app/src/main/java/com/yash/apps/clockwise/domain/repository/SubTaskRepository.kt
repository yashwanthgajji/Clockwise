package com.yash.apps.clockwise.domain.repository

import com.yash.apps.clockwise.domain.model.SubTask
import kotlinx.coroutines.flow.Flow

interface SubTaskRepository {
    suspend fun insertSubTask(subTask: SubTask)
    suspend fun updateSubTask(subTask: SubTask)
    suspend fun deleteSubTask(subTask: SubTask)
    fun getAllSubTasksStream(): Flow<List<SubTask>>
    fun getSubTaskStream(id:Int): Flow<SubTask?>
    fun getAllSubTasksOfTaskStream(taskId: Int): Flow<List<SubTask>>
}