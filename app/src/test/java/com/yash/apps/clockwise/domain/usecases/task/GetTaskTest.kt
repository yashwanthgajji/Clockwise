package com.yash.apps.clockwise.domain.usecases.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository
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

class GetTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getTask: GetTask
    private lateinit var mockRepository: TaskRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        getTask = GetTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getTaskUseCase_hasTaskWithId_returnsTask() = runTest {
        val testTaskId = 12
        val testTask = Task(tId = testTaskId, tName = "Test task")
        every { mockRepository.getTaskStream(testTaskId) } returns flowOf(testTask)
        getTask(taskId = testTaskId).test {
            val actualTask = awaitItem()
            assertEquals(testTask, actualTask)
            awaitComplete()
        }
    }

    @Test
    fun getTaskUseCase_noTaskForThisId_returnsNothing() = runTest {
        val testTaskId = 12
        every { mockRepository.getTaskStream(testTaskId) } returns flowOf(null)
        getTask(taskId = testTaskId).test {
            val actualTask = awaitItem()
            assertNull(actualTask)
            awaitComplete()
        }
    }
}