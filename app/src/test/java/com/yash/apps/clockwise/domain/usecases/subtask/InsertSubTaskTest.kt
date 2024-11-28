package com.yash.apps.clockwise.domain.usecases.subtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InsertSubTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var insertSubTask: InsertSubTask
    private lateinit var mockRepository: SubTaskRepository

    private val subTask = SubTask(sId = 11, sName = "SubTask1", sTaskId = 1)

    @Before
    fun setup() {
        mockRepository = mockk()
        insertSubTask = InsertSubTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertSubTaskUseCase_insertsSubTasks() = runTest {
        coEvery { mockRepository.insertSubTask(subTask) } returns Unit
        insertSubTask(subTask)
        coVerify(exactly = 1) { mockRepository.insertSubTask(subTask) }
    }
}