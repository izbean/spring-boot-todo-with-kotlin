package com.example.todo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            this.title = "제목"
            this.description = ""
            this.schedule = "2022-10-01 12:00:00"
        }

        val result = validator.validate(todoDto)

        assertEquals(true, result.isEmpty())
    }

}