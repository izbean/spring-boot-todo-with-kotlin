package com.example.todo.database

import com.example.todo.model.TodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var schedule: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)

fun Todo.convertTodo(todoDto: TodoDto): Todo {
    return Todo().apply {
        this.id = todoDto.id
        this.title = todoDto.title
        this.description = todoDto.description
        this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdAt = todoDto.createAt
        this.updatedAt = todoDto.updatedAt
    }
}