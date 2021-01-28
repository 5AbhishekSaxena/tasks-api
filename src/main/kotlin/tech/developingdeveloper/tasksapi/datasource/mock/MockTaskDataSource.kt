package tech.developingdeveloper.tasksapi.datasource.mock

import org.springframework.stereotype.Repository
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.model.Task

@Repository
class MockTaskDataSource : TaskDataSource {

    private val tasks = mutableListOf(
        Task("some title", "some details", "HIGH", 1)
    )

    override fun retrieveTasks() = tasks

    override fun retrieveTask(taskId: Int) = tasks.firstOrNull {
        it.id == taskId
    }

    override fun addTask(task: Task) = tasks.add(task)

    override fun updateTask(task: Task): Boolean {
        tasks.removeIf { it.id == task.id }

        return tasks.add(task)
    }

    override fun deleteTask(taskId: Int) = tasks.removeIf { it.id == taskId }
}