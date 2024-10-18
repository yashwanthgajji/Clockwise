package com.yash.apps.clockwise.data.record

import com.yash.apps.clockwise.model.Record
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao): RecordRepository {
    override suspend fun insertRecord(record: Record) = recordDao.insert(record)
    override suspend fun updateRecord(record: Record) = recordDao.update(record)
    override suspend fun deleteRecord(record: Record) = recordDao.delete(record)
    override fun getRecordStream(recordId: Int): Flow<Record?> {
        return recordDao.getRecord(recordId)
    }
    override fun getRecordByTaskIdStream(taskId: Int): Flow<List<Record>> {
        return recordDao.getRecordByTaskId(taskId)
    }
}