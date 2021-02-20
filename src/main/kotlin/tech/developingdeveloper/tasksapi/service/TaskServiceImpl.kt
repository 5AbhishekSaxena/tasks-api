package tech.developingdeveloper.tasksapi.service

import org.springframework.stereotype.Service
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.dto.TaskDTO
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.utils.mapper.TaskMapper

@Service("TaskService")
class TaskServiceImpl(
    private val dataSource: TaskDataSource,
    private val taskMapper: TaskMapper
) : TaskService {

    @Throws(TaskException::class)
    override fun retrieveTasks(): Collection<TaskDTO> {

        val tasks = dataSource.retrieveTasks()

        if (tasks.isNullOrEmpty())
            throw TaskException("List is empty")

        return taskMapper.fromEntityList(tasks)
    }

    @Throws(TaskException::class)
    override fun retrieveTask(taskId: Int): TaskDTO =
        taskMapper.fromEntity(
            dataSource.retrieveTask(taskId) ?: throw TaskException("Task doesn't exist with id: $taskId")
        )

    @Throws(TaskException::class)
    override fun addTask(task: TaskDTO): TaskDTO {

        if (task.id != null)
            throw TaskException("Task Id must be null")

        val newTask = dataSource.addTask(taskMapper.toEntity(task))

        return taskMapper.fromEntity(newTask)
    }


    @Throws(TaskException::class)
    override fun updateTask(task: TaskDTO): TaskDTO {

        task.id?.let {
            retrieveTask(task.id)
        } ?: throw TaskException("Task Id must not be null")

        dataSource.updateTask(taskMapper.toEntity(task))

        return task
    }

    @Throws(TaskException::class)
    override fun deleteTask(taskId: Int): TaskDTO {
        val task = dataSource.retrieveTask(taskId) ?: throw TaskException("Task doesn't exit with id: $taskId")

        dataSource.deleteTask(taskId)

        return taskMapper.fromEntity(task)
    }

}