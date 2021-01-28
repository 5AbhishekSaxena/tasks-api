package tech.developingdeveloper.tasksapi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task
import kotlin.jvm.Throws

@Service("TaskService")
class TaskServiceImpl @Autowired constructor(private val dataSource: TaskDataSource) : TaskService {

    @Throws(TaskException::class)
    override fun retrieveTasks(): Collection<Task> {

        val tasks = dataSource.retrieveTasks()

        if (tasks.isNullOrEmpty())
            throw TaskException("List is empty")

        return tasks
    }

    override fun retrieveTask(taskId: Int): Task =
        dataSource.retrieveTask(taskId) ?: throw TaskException("Task doesn't exist with id: $taskId")

}