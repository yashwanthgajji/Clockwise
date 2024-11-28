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

class DeleteSubTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var deleteSubTask: DeleteSubTask
    private lateinit var mockRepository: SubTaskRepository

    private val subTask = SubTask(sId = 11, sName = "SubTask1", sTaskId = 1)

    @Before
    fun setup() {
        mockRepository = mockk()
        deleteSubTask = DeleteSubTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun deleteSubTaskUseCase_deletesSubTasks() = runTest {
        coEvery { mockRepository.deleteSubTask(subTask) } returns Unit
        deleteSubTask(subTask)
        coVerify(exactly = 1) { mockRepository.deleteSubTask(subTask) }
    }
}