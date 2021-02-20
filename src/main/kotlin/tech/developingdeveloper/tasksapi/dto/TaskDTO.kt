package tech.developingdeveloper.tasksapi.dto

import tech.developingdeveloper.tasksapi.utils.validation.ValidateEnum
import javax.validation.constraints.NotNull


data class TaskDTO(
    @get:NotNull(message = "Title must not be null") val title: String? = null,
    @get:NotNull(message = "Details must not be null") val details: String? = null,
    @get:NotNull(message = "Priority must not be null") @ValidateEnum(
        enumClass = Priority::class,
        message = "Priority must be either 'LOW', 'MEDIUM' or 'HIGH'"
    ) val priority: String? = null,
    val id: Int?
)
