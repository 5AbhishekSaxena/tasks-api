package tech.developingdeveloper.tasksapi.utils.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tech.developingdeveloper.tasksapi.dto.Priority
import tech.developingdeveloper.tasksapi.dto.TaskDTO
import tech.developingdeveloper.tasksapi.model.Task

internal class TaskMapperTest {

    private val taskMapper: TaskMapper = TaskMapper()

    @Test
    fun `should return object of Task`() {
        // given
        val taskDTO = TaskDTO("Some title", "Some Details", "HIGH", 1)

        // when
        val result = taskMapper.toEntity(taskDTO)

        // then
        val task = Task("Some title", "Some Details", Priority.HIGH, 1)
        assertEquals(task, result)

    }

    @Test
    fun `should return object of TaskDTO`() {
        // given
        val task = Task("Some title", "Some Details", Priority.HIGH, 1)

        // when
        val result = taskMapper.fromEntity(task)

        // then
        val taskDTO = TaskDTO("Some title", "Some Details", "HIGH", 1)
        assertEquals(taskDTO, result)

    }

    @Test
    fun `should return list of Task`() {
        // given
        val taskDTOs = listOf(
            TaskDTO("Some title", "Some Details", "HIGH", 1),
            TaskDTO("Some title", "Some Details", "LOW", 2)
        )

        // when
        val result = taskMapper.toEntityList(taskDTOs)

        // then
        val task = listOf(
            Task("Some title", "Some Details", Priority.HIGH, 1),
            Task("Some title", "Some Details", Priority.LOW, 2)
        )
        assertEquals(task, result)

    }

    @Test
    fun `should return list of TaskDTO`() {
        // given
        val tasks = listOf(
            Task("Some title", "Some Details", Priority.HIGH, 1),
            Task("Some title", "Some Details", Priority.LOW, 2)
        )

        // when
        val result = taskMapper.fromEntityList(tasks)

        // then
        val taskDTOs = listOf(
            TaskDTO("Some title", "Some Details", "HIGH", 1),
            TaskDTO("Some title", "Some Details", "LOW", 2)
        )
        assertEquals(taskDTOs, result)

    }
}