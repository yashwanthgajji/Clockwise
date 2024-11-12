package com.yash.apps.clockwise.domain.usecases.subtask

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import kotlinx.coroutines.flow.Flow

class GetSubTaskByTask(private val subTaskRepository: SubTaskRepository) {
    operator fun invoke(taskId: Int): Flow<List<SubTask>> {
        return subTaskRepository.getAllSubTasksOfTaskStream(taskId)
    }
}