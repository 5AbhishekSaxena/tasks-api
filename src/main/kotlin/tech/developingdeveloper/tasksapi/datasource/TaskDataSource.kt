package tech.developingdeveloper.tasksapi.datasource

import tech.developingdeveloper.tasksapi.model.Task


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

interface TaskDataSource {

    fun retrieveTasks(): Collection<Task>
}