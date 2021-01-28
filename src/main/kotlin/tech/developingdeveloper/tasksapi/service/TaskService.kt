package tech.developingdeveloper.tasksapi.service

import tech.developingdeveloper.tasksapi.model.Task


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

interface TaskService {

    fun retrieveTasks(): Collection<Task>
    fun retrieveTask(taskId: Int): Task
    fun addTask(task: Task): Task
    fun updateTask(task: Task): Task
    fun deleteTask(taskId: Int): Task
}