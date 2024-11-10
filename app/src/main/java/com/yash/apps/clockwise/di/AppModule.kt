package com.yash.apps.clockwise.di

import android.app.Application
import androidx.room.Room
import com.yash.apps.clockwise.data.local.TaskDatabase
import com.yash.apps.clockwise.data.local.TaskTypeConverter
import com.yash.apps.clockwise.data.local.record.RecordDao
import com.yash.apps.clockwise.data.local.subtask.SubTaskDao
import com.yash.apps.clockwise.data.local.task.TaskDao
import com.yash.apps.clockwise.data.repository.OfflineRecordRepository
import com.yash.apps.clockwise.data.repository.OfflineSubTaskRepository
import com.yash.apps.clockwise.data.repository.OfflineTaskRepository
import com.yash.apps.clockwise.domain.repository.RecordRepository
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import com.yash.apps.clockwise.domain.repository.TaskRepository
import com.yash.apps.clockwise.domain.usecases.record.GetRecordDetails
import com.yash.apps.clockwise.domain.usecases.record.GetRecordDetailsBySubTask
import com.yash.apps.clockwise.domain.usecases.record.GetRecordDetailsByTask
import com.yash.apps.clockwise.domain.usecases.record.InsertRecord
import com.yash.apps.clockwise.domain.usecases.record.RecordUseCases
import com.yash.apps.clockwise.domain.usecases.task.GetTasks
import com.yash.apps.clockwise.domain.usecases.task.InsertTask
import com.yash.apps.clockwise.domain.usecases.task.TaskUseCases
import com.yash.apps.clockwise.util.Constants.TASK_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(application: Application): TaskDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = TaskDatabase::class.java,
            name = TASK_DATABASE_NAME
        ).addTypeConverter(TaskTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideSubTaskDao(taskDatabase: TaskDatabase): SubTaskDao {
        return taskDatabase.subTaskDao()
    }

    @Provides
    @Singleton
    fun provideRecordDao(taskDatabase: TaskDatabase): RecordDao {
        return taskDatabase.recordDao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return OfflineTaskRepository(taskDao = taskDao)
    }

    @Provides
    @Singleton
    fun provideSubTaskRepository(subTaskDao: SubTaskDao): SubTaskRepository {
        return OfflineSubTaskRepository(subTaskDao = subTaskDao)
    }

    @Provides
    @Singleton
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository {
        return OfflineRecordRepository(recordDao = recordDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(taskRepository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(taskRepository),
            insertTask = InsertTask(taskRepository)
        )
    }

    @Provides
    @Singleton
    fun provideRecordUseCases(recordRepository: RecordRepository): RecordUseCases {
        return RecordUseCases(
            getRecordDetails = GetRecordDetails(recordRepository),
            getRecordDetailsByTask = GetRecordDetailsByTask(recordRepository),
            getRecordDetailsBySubTask = GetRecordDetailsBySubTask(recordRepository),
            insertRecord = InsertRecord(recordRepository)
        )
    }
}