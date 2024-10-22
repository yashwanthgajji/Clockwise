package com.yash.apps.clockwise.data.record

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yash.apps.clockwise.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)
    @Update
    suspend fun update(record: Record)
    @Delete
    suspend fun delete(record: Record)
    @Query("SELECT * FROM records")
    fun getAllRecords(): Flow<List<Record>>
    @Query("SELECT * FROM records WHERE recordId = :recordId")
    fun getRecord(recordId: Int): Flow<Record>
    @Query("SELECT * FROM records WHERE taskId = :taskId")
    fun getRecordByTaskId(taskId: Int): Flow<List<Record>>
}