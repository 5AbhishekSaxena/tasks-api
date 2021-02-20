package tech.developingdeveloper.tasksapi.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.http.ResponseEntity

internal class ExceptionHandlerTest {

    private val exceptionHandler = ExceptionHandler()

    @Nested
    @DisplayName("generalExceptionHandler()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class HandleGeneralExceptions {

        @Test
        fun `should return object of ResponseEntity with ApiError type`() {
            // given
            val exception = Exception("Divide by zero")

            // when
            val result = exceptionHandler.generalExceptionHandler(exception)

            // then
            val apiError = ApiError(exception.message)
            val expected = ResponseEntity<ApiError>(apiError, apiError.error)

            assertEquals(expected, result)

        }

        @Test
        fun `should return object of ResponseEntity with ApiError type when exception doesn't have a message`() {
            // given
            val exception = Exception()

            // when
            val result = exceptionHandler.generalExceptionHandler(exception)

            // then
            val apiError = ApiError(null)
            val expected = ResponseEntity<ApiError>(apiError, apiError.error)

            assertEquals(expected, result)

        }
    }

    // check how to test ConstraintViolationException and MethodArgumentNotValidException
}