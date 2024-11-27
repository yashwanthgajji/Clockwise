package com.yash.apps.clockwise.domain.usecases.record

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.domain.model.RecordDetails
import com.yash.apps.clockwise.domain.repository.RecordRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class GetRecordDetailsBySubTaskTest  {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockRepository: RecordRepository
    private lateinit var getRecordDetailsBySubTask: GetRecordDetailsBySubTask

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
    private val recordDetails = recordDetailsOfSubTask1 + listOf(
        RecordDetails(
            rId = 112,
            rDate = Date(),
            rStartTime = Date(),
            rEndTime = Date(),
            rDuration = 0L,
            tId = 2,
            tName = "Test Task 2",
            sId = 12,
            sName = "Sub Task 2"
        )
    )

    @Before
    fun setup() {
        mockRepository = mockk()
        getRecordDetailsBySubTask = GetRecordDetailsBySubTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getRecordDetailsBySubTaskUseCase_hasRecordsOfSubTask_returnOnlyThoseRecords() = runTest{
        val subTaskId = 11
        every {
            mockRepository.getAllRecordDetailsBySubTaskStream(subTaskId)
        } returns flowOf(
            recordDetails.filter { recordDetails -> recordDetails.sId == subTaskId }
        )
        getRecordDetailsBySubTask(subTaskId).test {
            val actualRecords = awaitItem()
            assertEquals(recordDetailsOfSubTask1, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getRecordDetailsBySubTaskUseCase_noRecordsForSubTask_returnsEmptyList() = runTest{
        val subTaskId = 13
        every {
            mockRepository.getAllRecordDetailsBySubTaskStream(subTaskId)
        } returns flowOf(
            emptyList()
        )
        getRecordDetailsBySubTask(subTaskId).test {
            val actualRecords = awaitItem()
            assertTrue(actualRecords.isEmpty())
            awaitComplete()
        }
    }
}