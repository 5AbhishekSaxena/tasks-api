package tech.developingdeveloper.tasksapi.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import tech.developingdeveloper.tasksapi.datasource.TaskDataSource
import tech.developingdeveloper.tasksapi.dto.Priority
import tech.developingdeveloper.tasksapi.exception.TaskException
import tech.developingdeveloper.tasksapi.model.Task
import tech.developingdeveloper.tasksapi.utils.mapper.TaskMapper

internal class TaskServiceTest {

    private val taskDataSource = mockk<TaskDataSource>(relaxed = true)

    private val taskMapper = TaskMapper()

    private val taskService = TaskServiceImpl(taskDataSource, taskMapper)

    @Nested
    @DisplayName("Retrieve all tasks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RetrieveTasks {

        @Test
        fun `should retrieve tasks if list is not empty`() {

            // given
            val tasks = listOf(
                Task("some title", "some details", Priority.HIGH, 1)
            )

            every { taskDataSource.retrieveTasks() } returns tasks

            // when
            val result = assertDoesNotThrow { taskService.retrieveTasks() }

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
            val task = Task("some title", "some details", Priority.HIGH, 1)
            every { taskDataSource.retrieveTask(taskId) } returns task

            // when
            val result = assertDoesNotThrow { taskService.retrieveTask(taskId) }

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
            val task = Task("some title", "some details", Priority.HIGH, 1)
            every { taskDataSource.addTask(task) } returns task

            // when
            val result = assertDoesNotThrow { taskService.addTask(taskMapper.fromEntity(task)) }

            // then
            assertEquals(task, result)

        }

        @Test
        fun `should fail to add new task`() {
            // given
            val task = Task("some title", "some details", Priority.HIGH, 1)
            every { taskDataSource.addTask(task) } returns task

            // when
            val exception = assertThrows<TaskException> { taskService.addTask(taskMapper.fromEntity(task)) }

            // then
            assertEquals("Failed to add new task", exception.message)

        }
    }

    @Nested
    @DisplayName("Update task")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class UpdateTask {

        @Test
        fun `should update task`() {
            // given
            var count = 0
            val oldTask = Task("some task", "some details", Priority.HIGH, 1)
            val updatedTask = Task("new task", "new details", Priority.LOW, 1)

            every { taskDataSource.retrieveTask(1) } answers {
                count++
                if (count == 1)
                    oldTask
                else
                    updatedTask


            }

            every { taskDataSource.updateTask(updatedTask) } returns true

            // when
            val result = assertDoesNotThrow { taskService.updateTask(taskMapper.fromEntity(updatedTask)) }

            // then
            assertEquals(updatedTask, result)
            assertEquals(updatedTask, taskDataSource.retrieveTask(updatedTask.id!!))

        }

        @Test
        fun `should throw exception when update task`() {
            // given
            val updatedTask = Task("new task", "new details", Priority.LOW, 1)
            every { taskDataSource.retrieveTask(1) } returns null

            // when
            val result = assertThrows<TaskException> { taskService.updateTask(taskMapper.fromEntity(updatedTask)) }

            // then
            assertEquals("Task doesn't exit with id: ${updatedTask.id}", result.message)

        }

    }

    @Nested
    @DisplayName("Delete task")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteTask {

        @Test
        fun `should delete the task with the given id`() {
            // given
            val task = Task("some title", "some details", Priority.HIGH, 1)
            every { taskDataSource.retrieveTask(task.id!!) } returns task
            every { taskDataSource.deleteTask(task.id!!) } returns true

            // when
            val result = assertDoesNotThrow { taskService.deleteTask(task.id!!) }

            // then
            assertEquals(task, result)
        }

        @Test
        fun `should throw exception as invalid id is given`() {
            // given
            val taskId = 1
            every { taskDataSource.retrieveTask(taskId) } returns null

            // when
            val exception = assertThrows<TaskException> { taskService.deleteTask(taskId) }

            // then
            assertEquals("Task doesn't exit with id: $taskId", exception.message)

        }

    }


}