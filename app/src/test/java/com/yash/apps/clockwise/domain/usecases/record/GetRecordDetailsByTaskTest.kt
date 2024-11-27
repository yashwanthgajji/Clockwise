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


class GetRecordDetailsByTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockRepository: RecordRepository
    private lateinit var getRecordDetailsByTask: GetRecordDetailsByTask

    private val recordDetailsOfTask1 = listOf(
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

    @Before
    fun setup() {
        mockRepository = mockk()
        getRecordDetailsByTask = GetRecordDetailsByTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getRecordDetailsByTaskUseCase_hasRecordsOfTask_returnOnlyThoseRecords() = runTest{
        val taskId = 1
        every {
            mockRepository.getAllRecordDetailsByTaskStream(1)
        } returns flowOf(
            recordDetails.filter { recordDetails -> recordDetails.tId == taskId }
        )
        getRecordDetailsByTask(taskId).test {
            val actualRecords = awaitItem()
            assertEquals(recordDetailsOfTask1, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getRecordDetailsByTaskUseCase_noRecordsForTask_returnsEmptyList() = runTest{
        val taskId = 3
        every { mockRepository.getAllRecordDetailsByTaskStream(taskId) } returns flowOf(emptyList())
        getRecordDetailsByTask(taskId).test {
            val actualRecords = awaitItem()
            assertTrue(actualRecords.isEmpty())
            awaitComplete()
        }
    }
}