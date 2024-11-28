package com.yash.apps.clockwise.domain.usecases.record

data class RecordUseCases(
    val getRecordDetails: GetRecordDetails,
    val getRecordDetailsByTask: GetRecordDetailsByTask,
    val getRecordDetailsBySubTask: GetRecordDetailsBySubTask,
    val getReportDataOfCurrentData: GetReportDataOfCurrentData,
    val insertRecord: InsertRecord,
    val updateRecord: UpdateRecord,
    val deleteRecord: DeleteRecord
)
