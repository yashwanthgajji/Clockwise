package com.yash.apps.clockwise.data.subtask

import com.yash.apps.clockwise.model.SubTask
import kotlinx.coroutines.flow.Flow

interface SubTaskRepository {
    suspend fun insertTask(subTask: SubTask)
    suspend fun updateTask(subTask: SubTask)
    suspend fun deleteTask(subTask: SubTask)
    fun getAllSubTasksStream(): Flow<List<SubTask>>
    fun getSubTaskStream(id:Int): Flow<SubTask?>
    fun getAllSubTasksOfTaskStream(taskId: Int): Flow<List<SubTask>>
    fun getSubTaskNameById(id: Int): String
}