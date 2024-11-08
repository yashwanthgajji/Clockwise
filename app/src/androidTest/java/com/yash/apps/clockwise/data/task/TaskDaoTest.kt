package com.yash.apps.clockwise.data.task

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yash.apps.clockwise.data.TaskDatabase
import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.domain.model.Task
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    private lateinit var taskDao: TaskDao
    private lateinit var taskDatabase: TaskDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        taskDatabase = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java, )
            .allowMainThreadQueries()
            .build()
        taskDao = taskDatabase.taskDao()
    }

    private var task1 = Task(1, "Project")
    private var task2 = Task(2, "Complete Database", 1)
    private var task3 = Task(3, "Create Repository", 1)

    private var task4 = Task(4, "Add UI")

    private suspend fun addFirstTaskToDb() {
        taskDao.insert(task1)
    }

    private suspend fun addTwoTasksToDb() {
        taskDao.insert(task1)
        taskDao.insert(task2)
    }

    private suspend fun addParentTasksToDb() {
        taskDao.insert(task1)
        taskDao.insert(task4)
    }

    private suspend fun addSubTasksToDb() {
        taskDao.insert(task2)
        taskDao.insert(task3)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsTaskToDb() {
        runBlocking {
            addFirstTaskToDb()
            Assert.assertEquals(task1, taskDao.getAllTasks().first()[0])
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTasks_withValidTasks_returnsAllTasksFromDb() {
        runBlocking {
            addTwoTasksToDb()
            val allTasks = taskDao.getAllTasks().first()
            Assert.assertEquals(task1, allTasks[0])
            Assert.assertEquals(task2, allTasks[1])
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTask_withValidTask_returnsTask() {
        runBlocking {
            addFirstTaskToDb()
            val taskActual = taskDao.getTask(1).first()
            Assert.assertEquals(task1, taskActual)
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTask_withNonExistingTask_returnsNull() {
        runBlocking {
            addFirstTaskToDb()
            val taskActual = taskDao.getTask(0).first()
            Assert.assertNull(taskActual)
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateTask_updatesTaskInDb() {
        runBlocking {
            addFirstTaskToDb()
            taskDao.update(task1.copy(name = "Project Clock"))
            val taskActual = taskDao.getTask(1).first()
            Assert.assertEquals(Task(1, "Project Clock"), taskActual)
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteTask_deletesTaskInDb() {
        runBlocking {
            addFirstTaskToDb()
            taskDao.delete(task1)
            val allTasks = taskDao.getAllTasks().first()
            Assert.assertTrue(allTasks.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetSubTasks_parentHasSubTasks_returnsAllSubTasks() {
        runBlocking {
            addParentTasksToDb()
            addSubTasksToDb()
            val subTasks = taskDao.getSubTasks(1).first()
            Assert.assertEquals(task2, subTasks[0])
            Assert.assertEquals(task3, subTasks[1])
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetSubTasks_parentHasNoSubTasks_returnsEmptyList() {
        runBlocking {
            addParentTasksToDb()
            addSubTasksToDb()
            val subTasks = taskDao.getSubTasks(2).first()
            Assert.assertTrue(subTasks.isEmpty())
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        taskDatabase.close()
    }
}