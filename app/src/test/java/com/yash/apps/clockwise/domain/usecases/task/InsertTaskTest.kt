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

class InsertTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var insertTask: InsertTask
    private lateinit var mockRepository: TaskRepository

    private val testTask = Task(tId = 12, tName = "Test task")

    @Before
    fun setUp() {
        mockRepository = mockk()
        insertTask = InsertTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertTaskUseCase_insertsTask() = runTest{
        coEvery { mockRepository.insertTask(testTask) } returns Unit
        insertTask(testTask)
        coVerify(exactly = 1) { mockRepository.insertTask(testTask)}
    }
}