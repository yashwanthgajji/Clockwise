package com.yash.apps.clockwise.data.local.record

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.RecordDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)
    @Update
    suspend fun update(record: Record)
    @Delete
    suspend fun delete(record: Record)
    @Query("""SELECT r.rId, r.rDate, r.rStartTime, r.rEndTime, rDuration, t.tId, t.tName, s.sId, s.sName
        from records r 
        INNER JOIN tasks t ON r.rTaskId = t.tId
        LEFT OUTER JOIN subTasks s ON r.rSubTaskId = s.sId""")
    fun getAllRecordDetails(): Flow<List<RecordDetails>>
    @Query("""SELECT r.rId, r.rDate, r.rStartTime, r.rEndTime, rDuration, t.tId, t.tName, s.sId, s.sName
        from records r 
        INNER JOIN tasks t ON r.rTaskId = t.tId
        LEFT OUTER JOIN subTasks s ON r.rSubTaskId = s.sId
        WHERE t.tId = :taskId""")
    fun getRecordDetailsByTask(taskId: Int): Flow<List<RecordDetails>>
    @Query("""SELECT r.rId, r.rDate, r.rStartTime, r.rEndTime, rDuration, t.tId, t.tName, s.sId, s.sName
        from records r 
        INNER JOIN tasks t ON r.rTaskId = t.tId
        INNER JOIN subTasks s ON r.rSubTaskId = s.sId
        WHERE s.sId = :subTaskId""")
    fun getRecordDetailsBySubTask(subTaskId: Int): Flow<List<RecordDetails>>
}