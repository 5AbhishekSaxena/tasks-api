package tech.developingdeveloper.tasksapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.developingdeveloper.tasksapi.model.Task
import tech.developingdeveloper.tasksapi.service.TaskService
import tech.developingdeveloper.tasksapi.service.TaskServiceImpl


/**
 * Created by Abhishek Saxena on 24-01-2021.
 */

@RestController
@RequestMapping("/api/tasks")
class TaskController @Autowired constructor(
    private val taskService: TaskService
) {

    @GetMapping("/")
    fun getTasks(): Collection<Task> = taskService.retrieveTasks()
}