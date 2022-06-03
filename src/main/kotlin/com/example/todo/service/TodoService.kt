package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.convertTodo
import com.example.todo.model.TodoDto
import com.example.todo.model.convertTodoDto
import com.example.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TodoService(
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    fun createTodo(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun getTodos(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    fun getTodo(id: Int): TodoDto? {
        return todoRepositoryImpl.findOne(id)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun updateTodo(id: Int, todoDto: TodoDto): TodoDto? {
        return todoRepositoryImpl.findOne(id)?.apply {
            this.id = id
            this.title = todoDto.title
            this.description = todoDto.description
            this.updatedAt = LocalDateTime.now()
            this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }?. run {
            todoRepositoryImpl.save(this)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun deleteTodo(id: Int): Boolean {
        return todoRepositoryImpl.remove(id)
    }

}