package tech.developingdeveloper.tasksapi.model

import tech.developingdeveloper.tasksapi.dto.Priority


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

data class Task(
    val title: String,
    val details: String,
    val priority: Priority,
    var id: Int?
)