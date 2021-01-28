package tech.developingdeveloper.tasksapi.datasource

import tech.developingdeveloper.tasksapi.model.Task
import java.util.*


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

interface TaskDataSource {

    fun retrieveTasks(): Collection<Task>
    fun retrieveTask(taskId: Int): Task?
    fun addTask(task: Task): Boolean
    fun updateTask(task: Task): Boolean
    fun deleteTask(taskId: Int): Boolean

}