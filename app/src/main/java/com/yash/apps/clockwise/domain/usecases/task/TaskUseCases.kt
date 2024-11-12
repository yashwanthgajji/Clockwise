package com.yash.apps.clockwise.domain.usecases.task

data class TaskUseCases(
    val getTasks: GetTasks,
    val getTask: GetTask,
    val insertTask: InsertTask,
    val updateTask: UpdateTask,
    val deleteTask: DeleteTask
)