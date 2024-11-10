package com.yash.apps.clockwise.domain.usecases.subtask

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import kotlinx.coroutines.flow.Flow

class GetSubTasks(private val subTaskRepository: SubTaskRepository) {
    operator fun invoke(): Flow<List<SubTask>> {
        return subTaskRepository.getAllSubTasksStream()
    }
}