package com.yash.apps.clockwise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yash.apps.clockwise.data.local.record.RecordDao
import com.yash.apps.clockwise.data.local.subtask.SubTaskDao
import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.domain.model.Record
import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.model.Task

@Database(entities = [Task::class, SubTask::class, Record::class], version = 1, exportSchema = true)
@TypeConverters(TaskTypeConverter::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subTaskDao(): SubTaskDao
    abstract fun recordDao(): RecordDao
}