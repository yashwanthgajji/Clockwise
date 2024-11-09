package com.yash.apps.clockwise.domain.usecases.record

import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetRecordsByTask(private val recordRepository: RecordRepository) {
    operator fun invoke(taskId: Int): Flow<List<Record>> {
        return recordRepository.getRecordByTaskIdStream(taskId)
    }
}