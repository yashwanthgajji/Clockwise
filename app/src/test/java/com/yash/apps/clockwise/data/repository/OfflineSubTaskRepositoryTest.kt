package com.yash.apps.clockwise.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.data.local.subtask.SubTaskDao
import com.yash.apps.clockwise.domain.model.SubTask
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OfflineSubTaskRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockDao: SubTaskDao
    private lateinit var subTaskRepository: OfflineSubTaskRepository

    private val subTasksOfTask1 = listOf(
        SubTask(sId = 11, sName = "SubTask1", sTaskId = 1),
        SubTask(sId = 12, sName = "SubTask2", sTaskId = 1),
    )
    private val subTasksOfTask2 = listOf(
        SubTask(sId = 13, sName = "SubTask3", sTaskId = 2)
    )
    private val subTasks = subTasksOfTask1 + subTasksOfTask2

    @Before
    fun setUp() {
        mockDao = mockk()
        subTaskRepository = OfflineSubTaskRepository(mockDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertSubTask_insertsSubTaskIntoDatabase() = runTest {
        coEvery { mockDao.insert(subTasks[0]) } returns Unit
        subTaskRepository.insertSubTask(subTasks[0])
        coVerify(exactly = 1) { mockDao.insert(subTasks[0]) }
    }

    @Test
    fun updateSubTask_updatesSubTaskInDatabase() = runTest {
        val updatedSubTask = subTasks[0].copy(sName = "Updated Sub Task")
        coEvery { mockDao.update(updatedSubTask) } returns Unit
        subTaskRepository.updateSubTask(updatedSubTask)
        coVerify(exactly = 1) { subTaskRepository.updateSubTask(updatedSubTask) }
    }

    @Test
    fun deleteSubTask_deletesSubTaskInDatabase() = runTest {
        coEvery { mockDao.delete(subTasks[0]) } returns Unit
        subTaskRepository.deleteSubTask(subTasks[0])
        coVerify(exactly = 1) { mockDao.delete(subTasks[0]) }
    }

    @Test
    fun getAllSubTasksOfTaskStream_hasSubTasksForTask_returnsListOfSubTasks() = runTest {
        val taskId = 1
        every {
            mockDao.getAllSubTasksOfTask(taskId)
        } returns flowOf(
            subTasks.filter { subTask -> subTask.sTaskId == taskId }
        )
        subTaskRepository.getAllSubTasksOfTaskStream(taskId).test {
            val actualSubTasks = awaitItem()
            assertEquals(subTasksOfTask1, actualSubTasks)
            awaitComplete()
        }
    }

    @Test
    fun getAllSubTasksStream_hasSubTasks_returnsListOfAllSubTasks() = runTest {
        every { mockDao.getAllSubTasks() } returns flowOf(subTasks)
        subTaskRepository.getAllSubTasksStream().test {
            val actualSubTasks = awaitItem()
            assertEquals(subTasks, actualSubTasks)
            awaitComplete()
        }
    }

    @Test
    fun getSubTaskStream_hasSubTaskWithId_returnsSubTask() = runTest {
        val subTaskId = 11
        every { mockDao.getSubTask(subTaskId) } returns flowOf(subTasksOfTask1[0])
        subTaskRepository.getSubTaskStream(subTaskId).test {
            val actualSubTask = awaitItem()
            assertEquals(subTasksOfTask1[0], actualSubTask)
            awaitComplete()
        }
    }

}