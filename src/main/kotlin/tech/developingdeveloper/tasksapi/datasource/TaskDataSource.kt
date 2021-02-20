package tech.developingdeveloper.tasksapi.datasource

import tech.developingdeveloper.tasksapi.model.Task


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

interface TaskDataSource {

    fun retrieveTasks(): Collection<Task>
    fun retrieveTask(taskId: Int): Task?
    fun addTask(task: Task): Task
    fun updateTask(task: Task): Boolean
    fun deleteTask(taskId: Int): Boolean

}