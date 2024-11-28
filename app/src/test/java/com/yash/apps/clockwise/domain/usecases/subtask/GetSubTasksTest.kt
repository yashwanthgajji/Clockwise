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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSubTasksTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getSubTasks: GetSubTasks
    private lateinit var mockRepository: SubTaskRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        getSubTasks = GetSubTasks(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getSubTasksUseCase_hasSubTasks_returnsListOfAllSubTasks() = runTest {
        val subTasks = listOf(
            SubTask(sId = 11, sName = "SubTask1", sTaskId = 1),
            SubTask(sId = 12, sName = "SubTask2", sTaskId = 1),
            SubTask(sId = 13, sName = "SubTask3", sTaskId = 2),
        )
        every { mockRepository.getAllSubTasksStream() } returns flowOf(subTasks)
        getSubTasks().test {
            val actualSubTasks = awaitItem()
            assertEquals(subTasks, actualSubTasks)
            awaitComplete()
        }
    }

    @Test
    fun getSubTasksUseCase_noSubTasks_returnsEmptyList() = runTest {
        every { mockRepository.getAllSubTasksStream() } returns flowOf(emptyList())
        getSubTasks().test {
            val actualSubTasks = awaitItem()
            assertTrue(actualSubTasks.isEmpty())
            awaitComplete()
        }
    }
}