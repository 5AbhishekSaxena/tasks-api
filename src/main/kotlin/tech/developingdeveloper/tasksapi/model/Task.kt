package tech.developingdeveloper.tasksapi.model


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

data class Task(
    val title: String,
    val details: String,
    val priority: String,
    val id: Int = 0
)