package com.example.todo.database

data class TodoDataBase(
    var id: Int = 0,
    var todoLists: MutableList<Todo> = mutableListOf()

) {
    fun init() {
        id = 0
        this.todoLists = mutableListOf()
        println("[DEBUG] todo database init")
    }
}