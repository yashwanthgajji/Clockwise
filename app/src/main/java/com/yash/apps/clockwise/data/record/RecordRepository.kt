package com.yash.apps.clockwise.data.record

import com.yash.apps.clockwise.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun insertRecord(record: Record)
    suspend fun updateRecord(record: Record)
    suspend fun deleteRecord(record: Record)
    fun getAllRecordsStream(): Flow<List<Record>>
    fun getRecordStream(recordId: Int): Flow<Record?>
    fun getRecordByTaskIdStream(taskId: Int): Flow<List<Record>>
}