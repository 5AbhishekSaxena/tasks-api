package tech.developingdeveloper.tasksapi.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task

internal class TaskServiceTest {

    private val mockTaskDataSource = mockk<TaskDataSource>()


    private val taskService = TaskServiceImpl(mockTaskDataSource)

    @Test
    fun `should retrieve tasks if list is not empty`() {

        // given
        val tasks = listOf(
            Task(1, "some title", "some details", "HIGH")
        )

        every { mockTaskDataSource.retrieveTasks() } returns tasks

        // when
        val result = taskService.retrieveTasks()

        // then
        assertEquals(tasks, result)

    }

    @Test
    fun `should throw exception if the list of tasks is empty`() {

        // given
        every { mockTaskDataSource.retrieveTasks() } returns emptyList()

        // when
        val exception = assertThrows<TaskException> { taskService.retrieveTasks() }

        // then
        assertEquals("List is empty", exception.message)

    }

}