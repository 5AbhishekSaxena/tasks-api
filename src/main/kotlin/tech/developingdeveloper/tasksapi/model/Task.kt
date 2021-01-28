package tech.developingdeveloper.tasksapi.model


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

data class Task(
    val id: Int,
    val title: String,
    val details: String,
    val priority: String
)