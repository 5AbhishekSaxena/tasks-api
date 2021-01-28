package tech.developingdeveloper.tasksapi.datasource.mock

import org.springframework.stereotype.Repository
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task

@Repository
class MockTaskDataSource : TaskDataSource {

    private val tasks = listOf(
        Task(1, "some title", "some details", "HIGH")
    )

    override fun retrieveTasks(): Collection<Task> =
        if (tasks.isNullOrEmpty()) throw TaskException("List of tasks is empty") else tasks
}