package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl(
    val todoDataBase: TodoDataBase
) : TodoRepository {

    override fun save(todo: Todo): Todo? {
        return todo.id?.let {id ->

            findOne(id)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }

        }?: kotlin.run {
            return todo.apply {
                this.id = ++todoDataBase.id
                this.createdAt = LocalDateTime.now()
            }.run {
                todoDataBase.todoLists.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun remove(id: Int): Boolean {
        val todo = findOne(id)

        return todo?.let {
            todoDataBase.todoLists.remove(it)
            true
        }.run {
            false
        }
    }

    override fun findOne(id: Int): Todo? {
        return todoDataBase.todoLists.first { it.id == id }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoLists
    }

}