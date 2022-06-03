package com.example.todo.controller

import com.example.todo.model.TodoDto
import com.example.todo.service.TodoService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {

    @GetMapping("/{id}")
    fun getTodo(
        @PathVariable id: Int
    ): TodoDto? {
        return todoService.getTodo(id)
    }

    @GetMapping
    fun getTodos(): MutableList<TodoDto> {
        return todoService.getTodos()
    }

    // C
    @PostMapping
    fun createTodo(
        @Valid @RequestBody todoDto: TodoDto
    ): TodoDto? {
        return todoService.createTodo(todoDto)
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Int,
        @Valid @RequestBody todoDto: TodoDto
    ): TodoDto? {
        return todoService.updateTodo(id, todoDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(
        @PathVariable id: Int
    ): Boolean {
        return todoService.deleteTodo(id)
    }


}