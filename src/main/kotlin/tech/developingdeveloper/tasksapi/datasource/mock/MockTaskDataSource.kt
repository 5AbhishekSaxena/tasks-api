package tech.developingdeveloper.tasksapi.datasource.mock

import org.springframework.stereotype.Repository
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.model.Task

@Repository
class MockTaskDataSource : TaskDataSource {

    private val tasks = mutableListOf(
        Task("some title", "some details", "HIGH", 1)
    )

    override fun retrieveTasks(): Collection<Task> = tasks

    override fun retrieveTask(taskId: Int): Task? = tasks.firstOrNull {
        it.id == taskId
    }

    override fun addTask(task: Task): Boolean = tasks.add(task)
}