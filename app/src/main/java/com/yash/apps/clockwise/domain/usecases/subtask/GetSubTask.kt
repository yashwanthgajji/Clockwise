package com.yash.apps.clockwise.domain.usecases.subtask

import com.yash.apps.clockwise.domain.model.SubTask
import com.yash.apps.clockwise.domain.repository.SubTaskRepository
import kotlinx.coroutines.flow.Flow

class GetSubTask(private val subTaskRepository: SubTaskRepository) {
    operator fun invoke(subTaskId: Int): Flow<SubTask?> {
        return subTaskRepository.getSubTaskStream(subTaskId)
    }
}