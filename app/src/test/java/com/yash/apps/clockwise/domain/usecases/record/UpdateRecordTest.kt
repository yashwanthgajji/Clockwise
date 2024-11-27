package com.yash.apps.clockwise.domain.usecases.record

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.repository.RecordRepository
import com.yash.apps.clockwise.util.DateUtil.getFirstDayOfCurrentMonth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class UpdateRecordTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var updateRecord: UpdateRecord
    private lateinit var mockRepository: RecordRepository

    private val recordForTest = Record(
        rId = 111,
        rDate = Date(),
        rStartTime = Date(),
        rEndTime = Date(),
        rDuration = 0L,
        rTaskId = 1,
        rSubTaskId = 11,
    )

    @Before
    fun setup() {
        mockRepository = mockk()
        updateRecord = UpdateRecord(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun updateRecordUseCase_updatesRecord() = runTest{
        val updatedRecord = recordForTest.copy(rDate = getFirstDayOfCurrentMonth())
        coEvery { mockRepository.updateRecord(updatedRecord) } returns Unit
        updateRecord(updatedRecord)
        coVerify(exactly = 1) { mockRepository.updateRecord(updatedRecord) }
    }
}