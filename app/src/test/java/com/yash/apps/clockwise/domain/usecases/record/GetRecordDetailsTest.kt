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

class GetRecordDetailsTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockRepository: RecordRepository
    private lateinit var getRecordDetails: GetRecordDetails

    @Before
    fun setup() {
        mockRepository = mockk()
        getRecordDetails = GetRecordDetails(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getRecordDetailsUseCase_hasRecords_returnAllData() = runTest{
        val recordDetails = listOf(
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
                rId = 112,
                rDate = Date(),
                rStartTime = Date(),
                rEndTime = Date(),
                rDuration = 0L,
                tId = 2,
                tName = "Test Task 2"
            )
        )
        every { mockRepository.getAllRecordDetailsStream() } returns flowOf(recordDetails)
        getRecordDetails().test {
            val actualRecords = awaitItem()
            assertEquals(recordDetails, actualRecords)
            awaitComplete()
        }
    }

    @Test
    fun getRecordDetailsUseCase_noRecords_returnsEmptyList() = runTest{
        every { mockRepository.getAllRecordDetailsStream() } returns flowOf(emptyList())
        getRecordDetails().test {
            val actualRecords = awaitItem()
            assertTrue(actualRecords.isEmpty())
            awaitComplete()
        }
    }
}