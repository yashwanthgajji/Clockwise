package com.yash.apps.clockwise.domain.usecases.task

import com.yash.apps.clockwise.domain.model.Task
import com.yash.apps.clockwise.domain.repository.TaskRepository

class InsertTask(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        taskRepository.insertTask(task)
    }
}