package com.yash.apps.clockwise.domain.usecases.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpdateTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var updateTask: UpdateTask
    private lateinit var mockRepository: TaskRepository

    private val testTask = Task(tId = 12, tName = "Test task")

    @Before
    fun setUp() {
        mockRepository = mockk()
        updateTask = UpdateTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun updateTaskUseCase_updatesTask() = runTest{
        val updatedTask = testTask.copy(tName = "Updated Task")
        coEvery { mockRepository.updateTask(updatedTask) } returns Unit
        updateTask(updatedTask)
        coVerify(exactly = 1) { mockRepository.updateTask(updatedTask)}
    }
}