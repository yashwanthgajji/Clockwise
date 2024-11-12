package com.yash.apps.clockwise.data.repository

import com.yash.apps.clockwise.data.local.record.RecordDao
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao): RecordRepository {
    override suspend fun insertRecord(record: Record) = recordDao.insert(record)
    override suspend fun updateRecord(record: Record) = recordDao.update(record)
    override suspend fun deleteRecord(record: Record) = recordDao.delete(record)
    override fun getAllRecordDetailsStream(): Flow<List<RecordDetails>> {
        return recordDao.getAllRecordDetails()
    }
    override fun getAllRecordDetailsByTaskStream(taskId: Int): Flow<List<RecordDetails>> {
        return recordDao.getRecordDetailsByTask(taskId)
    }
    override fun getAllRecordDetailsBySubTaskStream(subTaskId: Int): Flow<List<RecordDetails>> {
        return recordDao.getRecordDetailsBySubTask(subTaskId)
    }
}