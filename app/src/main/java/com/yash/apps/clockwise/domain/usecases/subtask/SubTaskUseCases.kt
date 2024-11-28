package com.yash.apps.clockwise.domain.usecases.subtask

data class SubTaskUseCases(
    val getSubTasks: GetSubTasks,
    val getSubTask: GetSubTask,
    val getSubTaskByTask: GetSubTaskByTask,
    val insertSubTask: InsertSubTask,
    val updateSubTask: UpdateSubTask,
    val deleteSubTask: DeleteSubTask
)
