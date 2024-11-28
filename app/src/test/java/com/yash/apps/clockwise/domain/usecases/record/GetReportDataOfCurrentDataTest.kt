package com.yash.apps.clockwise.domain.usecases.record

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.domain.model.ReportDataValue
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

class GetReportDataOfCurrentDataTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getReportDataOfCurrentData: GetReportDataOfCurrentData
    private lateinit var mockRepository: RecordRepository

    private val date1 = Date(1732590712294L)
    private val date2 = Date(1731726712294L)

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
        mockRepository = mockk()
        getReportDataOfCurrentData = GetReportDataOfCurrentData(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getReportDataOfCurrentDate_hasRecordsForDate_returnsOnlyThoseRecords() = runTest {
        every {
            mockRepository.getReportDataOfCurrentDateStream(date1)
        } returns flowOf(
            reportDataOfData1
        )
        getReportDataOfCurrentData(date1).test {
            val actualData = awaitItem()
            assertEquals(reportDataOfData1, actualData)
            awaitComplete()
        }
    }

    @Test
    fun getReportDataOfCurrentDate_noRecordsForDate_returnsEmptyList() = runTest {
        every {
            mockRepository.getReportDataOfCurrentDateStream(date2)
        } returns flowOf(
            emptyList()
        )
        getReportDataOfCurrentData(date2).test {
            val actualData = awaitItem()
            assertTrue(actualData.isEmpty())
            awaitComplete()
        }
    }
}