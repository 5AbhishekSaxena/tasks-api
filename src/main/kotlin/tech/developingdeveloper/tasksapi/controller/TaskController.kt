package tech.developingdeveloper.tasksapi.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tech.developingdeveloper.tasksapi.dto.TaskDTO
import tech.developingdeveloper.tasksapi.service.TaskService
import javax.validation.Valid
import javax.validation.constraints.NotNull


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping("/")
    fun getTasks(): Collection<TaskDTO> = taskService.retrieveTasks()

    @GetMapping("/{taskId}")
    fun getTask(
        @PathVariable taskId: Int
    ): TaskDTO = taskService.retrieveTask(taskId)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTask(@RequestBody @Valid task: TaskDTO): TaskDTO = taskService.addTask(task)

    @PatchMapping("/")
    fun updateTask(
        @RequestBody @Valid task: TaskDTO
    ): TaskDTO = taskService.updateTask(task)

    @DeleteMapping("/")
    fun deleteTask(@RequestParam @NotNull taskId: Int): TaskDTO =
        taskService.deleteTask(taskId)
}