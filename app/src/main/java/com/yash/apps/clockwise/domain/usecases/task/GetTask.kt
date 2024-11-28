package com.yash.apps.clockwise.domain.usecases.task

import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTask(private val taskRepository: TaskRepository) {
    operator fun invoke(taskId: Int): Flow<Task?> {
        return taskRepository.getTaskStream(taskId)
    }
}