package tech.developingdeveloper.tasksapi.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tech.developingdeveloper.tasksapi.exception.TaskException


/**
 * Created by Abhishek Saxena on 28-01-2021.
 */

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(TaskException::class)
    fun taskExceptionHandler(exception: TaskException): ResponseEntity<String> =
        ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
}