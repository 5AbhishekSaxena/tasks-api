package tech.developingdeveloper.tasksapi.datasource.mock

import org.springframework.stereotype.Repository
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.dto.Priority
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task

@Repository
class MockTaskDataSource : TaskDataSource {

    private val tasks = mutableListOf(
        Task("some title1", "some details1", Priority.HIGH, 1),
        Task("some title2", "some details2", Priority.LOW, 2),
        Task("some title3", "some details3", Priority.MEDIUM, 3)
    )

    override fun retrieveTasks() = tasks

    override fun retrieveTask(taskId: Int) = tasks.firstOrNull {
        it.id == taskId
    }

    @Throws(TaskException::class)
    override fun addTask(task: Task): Task {
        task.id = tasks.size + 1

        val isAdded = tasks.add(task)

        if (!isAdded)
            throw TaskException("Failed to add new task")

        return task
    }

    override fun updateTask(task: Task): Boolean {
        tasks.removeIf { it.id == task.id }
        return tasks.add(task)
    }

    override fun deleteTask(taskId: Int) = tasks.removeIf { it.id == taskId }
}