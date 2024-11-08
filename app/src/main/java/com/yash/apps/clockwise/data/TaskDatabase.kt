package com.yash.apps.clockwise.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yash.apps.clockwise.data.local.record.RecordDao
import com.yash.apps.clockwise.data.local.subtask.SubTaskDao
import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task
import kotlin.concurrent.Volatile

@Database(entities = [Task::class, SubTask::class, Record::class], version = 1, exportSchema = true)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subTaskDao(): SubTaskDao
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null
        fun getDatabase(context: Context): TaskDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = TaskDatabase::class.java,
                    name = "task_database"
                ).build().also {
                    instance = it
                }
            }
        }
    }
}