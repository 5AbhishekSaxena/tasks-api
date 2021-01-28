package tech.developingdeveloper.tasksapi.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task

internal class TaskServiceTest {

    private val taskDataSource = mockk<TaskDataSource>()


    private val taskService = TaskServiceImpl(taskDataSource)


    @Nested
    @DisplayName("Retrieve all tasks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RetrieveTasks {


        @Test
        fun `should retrieve tasks if list is not empty`() {

            // given
            val tasks = listOf(
                Task("some title", "some details", "HIGH", 1)
            )

            every { taskDataSource.retrieveTasks() } returns tasks

            // when
            val result = taskService.retrieveTasks()

            // then
            assertEquals(tasks, result)

        }

        @Test
        fun `should throw exception if the list of tasks is empty`() {

            // given
            every { taskDataSource.retrieveTasks() } returns emptyList()

            // when
            val exception = assertThrows<TaskException> { taskService.retrieveTasks() }

            // then
            assertEquals("List is empty", exception.message)

        }
    }

    @Nested
    @DisplayName("Retrieve task using task id")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RetrieveTaskUsingId {

        @Test
        fun `should return task using task Id`() {
            // given
            val taskId = 1
            val task = Task("soem title", "some details", "HIGH", 1)
            every { taskDataSource.retrieveTask(taskId) } returns task

            // when
            val result = taskService.retrieveTask(taskId)

            // then
            assertEquals(task, result)

        }

        @Test
        fun `should throw TaskException if task with given id doesn't exist`() {
            // given
            val taskId = 1
            every { taskDataSource.retrieveTask(taskId) } throws NoSuchElementException()

            // when
            val exception = assertThrows<NoSuchElementException> { taskService.retrieveTask(taskId) }

            // then
            assertEquals(null, exception.message)

        }
    }

    @Nested
    @DisplayName("Add new task")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddTask {

        @Test
        fun `should successfully add new task`() {
            // given
            val task = Task("some title", "some details", "HIGH")
            every { taskDataSource.addTask(task) } returns true

            // when
            val result = taskService.addTask(task)

            // then
            assertEquals(task, result)

        }

        @Test
        fun `should fail to add new task`() {
            // given
            val task = Task("some title", "some details", "HIGH")
            every { taskDataSource.addTask(task) } returns false

            // when
            val exception = assertThrows<TaskException> { taskService.addTask(task) }

            // then
            assertEquals("Failed to add new task", exception.message)

        }
    }


}