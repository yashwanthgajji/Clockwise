package com.yash.apps.clockwise.data.local.subtask

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yash.apps.clockwise.domain.model.SubTask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subTask: SubTask)
    @Update
    suspend fun update(subTask: SubTask)
    @Delete
    suspend fun delete(subTask: SubTask)
    @Query("SELECT * FROM subTasks")
    fun getAllSubTasks(): Flow<List<SubTask>>
    @Query("SELECT * FROM subtasks WHERE sId = :id")
    fun getSubTask(id: Int): Flow<SubTask>
    @Query("SELECT * FROM subTasks WHERE sTaskId = :taskId")
    fun getAllSubTasksOfTask(taskId: Int): Flow<List<SubTask>>
}