package com.yash.apps.clockwise.domain.usecases.record

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetRecordDetailsByTask(private val recordRepository: RecordRepository) {
    operator fun invoke(taskId: Int): Flow<List<RecordDetails>> {
        return recordRepository.getAllRecordDetailsByTaskStream(taskId)
    }
}