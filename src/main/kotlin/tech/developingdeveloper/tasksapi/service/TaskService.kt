package tech.developingdeveloper.tasksapi.service

import tech.developingdeveloper.tasksapi.dto.TaskDTO


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

interface TaskService {

    fun retrieveTasks(): Collection<TaskDTO>
    fun retrieveTask(taskId: Int): TaskDTO
    fun addTask(task: TaskDTO): TaskDTO
    fun updateTask(task: TaskDTO): TaskDTO
    fun deleteTask(taskId: Int): TaskDTO
}