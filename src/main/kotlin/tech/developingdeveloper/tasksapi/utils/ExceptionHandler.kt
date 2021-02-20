package tech.developingdeveloper.tasksapi.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException


/**
 * Created by Abhishek Saxena on 28-01-2021.
 */

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ApiError> =
        ResponseEntity(ApiError(exception.message), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ConstraintViolationException::class, MethodArgumentNotValidException::class)
    fun constraintViolationExceptionHandler(exception: Exception): ResponseEntity<ApiError> {

        val errorMessage = when (exception) {
            is ConstraintViolationException -> exception.constraintViolations.joinToString(separator = ", ") {
                it.message
            }
            is MethodArgumentNotValidException -> {
                exception.bindingResult.allErrors.joinToString(", ") {
                    it.defaultMessage ?: ""
                }
            }
            else -> {
                exception.message
            }
        }

        val apiError = ApiError(errorMessage)
        return ResponseEntity(apiError, apiError.error)
    }
}