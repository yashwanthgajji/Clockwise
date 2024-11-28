package com.yash.apps.clockwise.domain.usecases.task

import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAllTasksStream()
    }
}