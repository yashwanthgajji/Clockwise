package com.yash.apps.clockwise.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.domain.model.Task
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

class OfflineTaskRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockDao: TaskDao
    private lateinit var taskRepository: OfflineTaskRepository

    private val tasks = listOf(
        Task(tId = 1, tName = "Test task 1"),
        Task(tId = 2, tName = "Test task 2"),
        Task(tId = 3, tName = "Test task 3"),
        Task(tId = 4, tName = "Test task 4")
    )

    @Before
    fun setup() {
        mockDao = mockk()
        taskRepository = OfflineTaskRepository(mockDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertTask_insertsTaskIntoDatabase() = runTest {
        coEvery { mockDao.insert(tasks[0]) } returns Unit
        taskRepository.insertTask(tasks[0])
        coVerify(exactly = 1) { mockDao.insert(tasks[0]) }
    }

    @Test
    fun updateTask_updatesTaskInDatabase() = runTest {
        val updatedTask = tasks[0].copy(tName = "Updated Task")
        coEvery { mockDao.update(updatedTask) } returns Unit
        taskRepository.updateTask(updatedTask)
        coVerify(exactly = 1) { taskRepository.updateTask(updatedTask) }
    }

    @Test
    fun deleteTask_deletesTaskInDatabase() = runTest {
        coEvery { mockDao.delete(tasks[0]) } returns Unit
        taskRepository.deleteTask(tasks[0])
        coVerify(exactly = 1) { mockDao.delete(tasks[0]) }
    }

    @Test
    fun gelAllTasksStream_hasTasks_returnsAll() = runTest {
        every { mockDao.getAllTasks() } returns flowOf(tasks)
        taskRepository.getAllTasksStream().test {
            val actualTasks = awaitItem()
            assertEquals(tasks, actualTasks)
            awaitComplete()
        }
    }

    @Test
    fun getTaskStream_hasTaskWithId_returnsThatTask() = runTest {
        every { mockDao.getTask(tasks[1].tId) } returns flowOf(tasks[1])
        taskRepository.getTaskStream(tasks[1].tId).test {
            val actualTask = awaitItem()
            assertEquals(tasks[1], actualTask)
            awaitComplete()
        }
    }
}