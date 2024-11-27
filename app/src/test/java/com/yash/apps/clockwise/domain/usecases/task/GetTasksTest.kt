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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetTasksTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getTasks: GetTasks
    private lateinit var mockRepository: TaskRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        getTasks = GetTasks(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getTasksUseCase_hasTasks_returnsListOfTasks() = runTest {
        val tasks = listOf(
            Task(tId = 1, tName = "Test task 1"),
            Task(tId = 2, tName = "Test task 2"),
            Task(tId = 3, tName = "Test task 3"),
            Task(tId = 4, tName = "Test task 4")
        )
        every { mockRepository.getAllTasksStream() } returns flowOf(tasks)
        getTasks().test {
            val actualTasks = awaitItem()
            assertEquals(tasks, actualTasks)
            awaitComplete()
        }
    }

    @Test
    fun getTasksUseCase_noTasks_returnsEmptyList() = runTest {
        every { mockRepository.getAllTasksStream() } returns flowOf(emptyList())
        getTasks().test {
            val actualTasks = awaitItem()
            assertTrue(actualTasks.isEmpty())
            awaitComplete()
        }
    }
}