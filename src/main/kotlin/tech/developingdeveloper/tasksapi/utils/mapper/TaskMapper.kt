package tech.developingdeveloper.tasksapi.utils.mapper

import org.springframework.stereotype.Service
import tech.developingdeveloper.tasksapi.dto.Priority
import tech.developingdeveloper.tasksapi.dto.TaskDTO
import tech.developingdeveloper.tasksapi.model.Task

@Service
class TaskMapper : ListMapper<TaskDTO, Task>, Mapper<TaskDTO, Task> {
    override fun fromEntity(task: Task): TaskDTO = TaskDTO(
        task.title,
        task.details,
        task.priority.name,
        task.id
    )

    override fun toEntity(taskDTO: TaskDTO): Task = Task(
        taskDTO.title!!,
        taskDTO.details!!,
        Priority.valueOf(taskDTO.priority!!),
        taskDTO.id
    )

    override fun toEntityList(taskDTOs: Collection<TaskDTO>): Collection<Task> = taskDTOs.map {
        toEntity(it)
    }

    override fun fromEntityList(tasks: Collection<Task>): Collection<TaskDTO> = tasks.map {
        fromEntity(it)
    }
}