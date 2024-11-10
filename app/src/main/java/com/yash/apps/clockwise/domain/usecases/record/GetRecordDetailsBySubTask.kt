package com.yash.apps.clockwise.domain.usecases.record

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetRecordDetailsBySubTask(private val recordRepository: RecordRepository) {
    operator fun invoke(subTaskId: Int): Flow<List<RecordDetails>> {
        return recordRepository.getAllRecordDetailsBySubTaskStream(subTaskId)
    }
}