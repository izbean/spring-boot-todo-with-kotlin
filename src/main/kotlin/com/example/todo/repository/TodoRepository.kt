package com.example.todo.repository

import com.example.todo.database.Todo

interface TodoRepository {

    fun save(todo: Todo): Todo?

    fun saveAll(todoList: MutableList<Todo>): Boolean

    fun remove(id: Int): Boolean

    fun findOne(id: Int): Todo?

    fun findAll(): MutableList<Todo>

}