package com.yash.apps.clockwise.domain.repository

import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.ReportDataValue
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RecordRepository {
    suspend fun insertRecord(record: Record)
    suspend fun updateRecord(record: Record)
    suspend fun deleteRecord(record: Record)
    fun getAllRecordDetailsStream(): Flow<List<RecordDetails>>
    fun getAllRecordDetailsByTaskStream(taskId: Int): Flow<List<RecordDetails>>
    fun getAllRecordDetailsBySubTaskStream(subTaskId: Int): Flow<List<RecordDetails>>
    fun getReportDataOfCurrentDateStream(date: Date): Flow<List<ReportDataValue>>
}