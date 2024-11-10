package com.yash.apps.clockwise.domain.usecases.record

import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.repository.RecordRepository

class InsertRecord(private val recordRepository: RecordRepository) {
    suspend operator fun invoke(record: Record) {
        recordRepository.insertRecord(record)
    }
}