package com.yash.apps.clockwise.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
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
    private var task2 = Task(2, "Complete Database")

    private suspend fun addFirstTaskToDb() {
        taskDao.insert(task1)
    }

    private suspend fun addAllTasksToDb() {
        taskDao.insert(task1)
        taskDao.insert(task2)
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
    fun daoGetAllTasks_returnsAllTasksFromDb() {
        runBlocking {
            addAllTasksToDb()
            val allTasks = taskDao.getAllTasks().first()
            Assert.assertEquals(task1, allTasks[0])
            Assert.assertEquals(task2, allTasks[1])
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTask_returnsTaskWithId() {
        runBlocking {
            addFirstTaskToDb()
            val taskActual = taskDao.getTask(1).first()
            Assert.assertEquals(task1, taskActual)
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

    @After
    @Throws(IOException::class)
    fun closeDb() {
        taskDatabase.close()
    }
}