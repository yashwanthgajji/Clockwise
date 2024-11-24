package com.yash.apps.clockwise.domain.usecases.record

import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class GetReportDataOfCurrentData(private val recordRepository: RecordRepository) {
    operator fun invoke(date: Date): Flow<List<ReportDataValue>> {
        return recordRepository.getReportDataOfCurrentDateStream(date)
    }
}