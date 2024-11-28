package com.yash.apps.clockwise.domain.usecases.record

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.repository.RecordRepository
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

class InsertRecordTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var insertRecord: InsertRecord
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
        insertRecord = InsertRecord(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertRecordUseCase_insertsRecord() = runTest{
        coEvery { mockRepository.insertRecord(recordForTest) } returns Unit
        insertRecord(recordForTest)
        coVerify(exactly = 1) { mockRepository.insertRecord(recordForTest) }
    }
}