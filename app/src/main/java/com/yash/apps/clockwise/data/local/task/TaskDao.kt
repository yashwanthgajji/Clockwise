package com.yash.apps.clockwise.data.local.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yash.apps.clockwise.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)
    @Update
    suspend fun update(task: Task)
    @Delete
    suspend fun delete(task: Task)
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>
    @Query("SELECT name FROM tasks WHERE id = :id")
    fun getTaskName(id: Int): String
}