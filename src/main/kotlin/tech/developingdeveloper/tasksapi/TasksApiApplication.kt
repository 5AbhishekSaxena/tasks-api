package tech.developingdeveloper.tasksapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TasksApiApplication

fun main(args: Array<String>) {
    runApplication<TasksApiApplication>(*args)
}
