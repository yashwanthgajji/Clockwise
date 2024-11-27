package com.yash.apps.clockwise.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.data.local.record.RecordDao
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.model.ReportDataValue
import com.yash.apps.clockwise.util.DateUtil.getFirstDayOfCurrentMonth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class OfflineRecordRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockDao: RecordDao
    private lateinit var recordRepository: OfflineRecordRepository

    private val recordForTest = Record(
        rId = 111,
        rDate = Date(),
        rStartTime = Date(),
        rEndTime = Date(),
        rDuration = 0L,
        rTaskId = 1,
        rSubTaskId = 11,
    )

    private val recordDetailsOfSubTask1 = listOf(
        RecordDetails(
            rId = 111,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = 0L,
            tId = 1,
            tName = "Test Task 1",
            sId = 11,
            sName = "Sub Task 1"
        ),
        RecordDetails(
            rId = 113,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = 0L,
            tId = 1,
            tName = "Test Task 1",
            sId = 11,
            sName = "Sub Task 1"
        ),
    )
    private val recordDetailsOfTask1 = recordDetailsOfSubTask1 + listOf(
        RecordDetails(
            rId = 114,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = 0L,
            tId = 1,
            tName = "Test Task 1",
        ),
    )
    private val recordDetails = recordDetailsOfTask1 + listOf(
        RecordDetails(
            rId = 112,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = 0L,
            tId = 2,
            tName = "Test Task 2"
        )
    )

    private val date1 = Date(1732590712294L)
    private val reportDataOfData1 = listOf(
        ReportDataValue(
            taskDuration = 178000L,
            taskName = "Task 1"
        ),
        ReportDataValue(
            taskDuration = 97000L,
            taskName = "Task 2"
        )
    )

    @Before
    fun setUp() {
        mockDao = mockk()
        recordRepository = OfflineRecordRepository(mockDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertRecord_insertsRecordIntoDatabase() = runTest{
        coEvery { mockDao.insert(recordForTest) } returns Unit
        recordRepository.insertRecord(recordForTest)
        coVerify(exactly = 1) { mockDao.insert(recordForTest) }
    }

    @Test
    fun updateRecord_updatesRecordInDatabase() = runTest{
        val updatedRecord = recordForTest.copy(rDate = getFirstDayOfCurrentMonth())
        coEvery { mockDao.update(updatedRecord) } returns Unit
        recordRepository.updateRecord(updatedRecord)
        coVerify(exactly = 1) { mockDao.update(updatedRecord) }
    }

    @Test
    fun deleteRecord_deletesRecordInDatabase() = runTest{
        coEvery { mockDao.delete(recordForTest) } returns Unit
        recordRepository.deleteRecord(recordForTest)
        coVerify(exactly = 1) { mockDao.delete(recordForTest) }
    }

    @Test
    fun getAllRecordDetailsStream_hasRecords_returnAllData() = runTest{
        every { mockDao.getAllRecordDetails() } returns flowOf(recordDetails)
        recordRepository.getAllRecordDetailsStream().test {
            val actualRecords = awaitItem()
            assertEquals(recordDetails, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getAllRecordDetailsByTaskStream_hasRecordsOfTask_returnOnlyThoseRecords() = runTest{
        val taskId = 1
        every {
            mockDao.getRecordDetailsByTask(taskId)
        } returns flowOf(
            recordDetails.filter { recordDetails -> recordDetails.tId == taskId }
        )
        recordRepository.getAllRecordDetailsByTaskStream(taskId).test {
            val actualRecords = awaitItem()
            assertEquals(recordDetailsOfTask1, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getAllRecordDetailsBySubTaskStream_hasRecordsOfSubTask_returnOnlyThoseRecords() = runTest{
        val subTaskId = 11
        every {
            mockDao.getRecordDetailsBySubTask(subTaskId)
        } returns flowOf(
            recordDetails.filter { recordDetails -> recordDetails.sId == subTaskId }
        )
        recordRepository.getAllRecordDetailsBySubTaskStream(subTaskId).test {
            val actualRecords = awaitItem()
            assertEquals(recordDetailsOfSubTask1, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getReportDataOfCurrentDateStream_hasRecordsForDate_returnsOnlyThoseRecords() = runTest {
        every {
            mockDao.getReportDataOfCurrentDate(date1)
        } returns flowOf(
            reportDataOfData1
        )
        recordRepository.getReportDataOfCurrentDateStream(date1).test {
            val actualData = awaitItem()
            assertEquals(reportDataOfData1, actualData)
            awaitComplete()
        }
    }
}