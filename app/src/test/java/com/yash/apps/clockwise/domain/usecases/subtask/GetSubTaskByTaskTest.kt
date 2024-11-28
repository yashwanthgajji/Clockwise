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

class GetSubTaskByTaskTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getSubTaskByTask: GetSubTaskByTask
    private lateinit var mockRepository: SubTaskRepository

    private val subTasksOfTask1 = listOf(
        SubTask(sId = 11, sName = "SubTask1", sTaskId = 1),
        SubTask(sId = 12, sName = "SubTask2", sTaskId = 1),
    )
    private val subTasksOfTask2 = listOf(
        SubTask(sId = 13, sName = "SubTask3", sTaskId = 2)
    )
    private val subTasks = subTasksOfTask1 + subTasksOfTask2

    @Before
    fun setup() {
        mockRepository = mockk()
        getSubTaskByTask = GetSubTaskByTask(mockRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getSubTaskByTaskUseCase_hasSubTasksForTask_returnsListOfSubTasks() = runTest {
        val taskId = 1
        every {
            mockRepository.getAllSubTasksOfTaskStream(taskId)
        } returns flowOf(
            subTasks.filter { subTask -> subTask.sTaskId == taskId }
        )
        getSubTaskByTask(taskId).test {
            val actualSubTasks = awaitItem()
            assertEquals(subTasksOfTask1, actualSubTasks)
            awaitComplete()
        }
    }

    @Test
    fun getSubTaskByTaskUseCase_noSubTasksForTask_returnsEmptyList() = runTest {
        val taskId = 3
        every {
            mockRepository.getAllSubTasksOfTaskStream(taskId)
        } returns flowOf(
            subTasks.filter { subTask -> subTask.sTaskId == taskId }
        )
        getSubTaskByTask(taskId).test {
            val actualSubTasks = awaitItem()
            assertTrue(actualSubTasks.isEmpty())
            awaitComplete()
        }
    }
}