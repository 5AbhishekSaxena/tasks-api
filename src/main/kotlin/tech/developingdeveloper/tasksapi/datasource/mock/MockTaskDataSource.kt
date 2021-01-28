package tech.developingdeveloper.tasksapi.datasource.mock

import org.springframework.stereotype.Repository
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.model.Task

@Repository
class MockTaskDataSource : TaskDataSource {

    private val tasks = listOf(
        Task(1, "some title", "some details", "HIGH")
    )

    override fun retrieveTasks(): Collection<Task> = tasks
}