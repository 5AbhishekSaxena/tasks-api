package tech.developingdeveloper.tasksapi.utils

import org.springframework.http.HttpStatus
import java.time.LocalDateTime


data class ApiError(
    private val _errorMessage: String?,
    val error: HttpStatus = HttpStatus.BAD_REQUEST,
    val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    val errorMessage: String
        get() = _errorMessage ?: "Something went wrong"

    val errorCode: Int
        get() = error.value()
}
