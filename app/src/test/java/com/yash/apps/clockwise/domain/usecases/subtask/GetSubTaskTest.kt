package com.yash.apps.clockwise.domain.usecases.subtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSubTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getSubTask: GetSubTask
    private lateinit var mockRepository: SubTaskRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        getSubTask = GetSubTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getSubTaskUseCase_hasSubTaskWithId_returnsSubTask() = runTest {
        val subTaskId = 11
        val subTask = SubTask(sId = 11, sName = "SubTask1", sTaskId = 1)
        every { mockRepository.getSubTaskStream(subTaskId) } returns flowOf(subTask)
        getSubTask(subTaskId).test {
            val actualSubTask = awaitItem()
            assertEquals(subTask, actualSubTask)
            awaitComplete()
        }
    }

    @Test
    fun getSubTaskUseCase_noSubTaskWithId_returnsNull() = runTest {
        every { mockRepository.getSubTaskStream(11) } returns flowOf(null)
        getSubTask(11).test {
            val actualSubTask = awaitItem()
            assertNull(actualSubTask)
            awaitComplete()
        }
    }
}