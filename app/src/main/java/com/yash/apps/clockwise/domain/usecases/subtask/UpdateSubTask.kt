package com.yash.apps.clockwise.domain.usecases.subtask

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository

class UpdateSubTask(private val subTaskRepository: SubTaskRepository) {
    suspend operator fun invoke(subTask: SubTask) {
        subTaskRepository.updateSubTask(subTask)
    }
}