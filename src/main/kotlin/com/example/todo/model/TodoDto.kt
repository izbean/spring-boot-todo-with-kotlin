package com.example.todo.model

import com.example.todo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDto(

    @field:ApiModelProperty(
        value = "index",
        example = "1",
        required = false
    )
    var id: Int? = null,

    @field:ApiModelProperty(
        value = "일정명",
        example = "테스트 일정",
        required = true
    )
    @field:NotBlank
    var title: String? = null,

    @field:ApiModelProperty(
        value = "일정설명",
        example = "테스트 일정 설명",
        required = false
    )
    var description: String? = null,

    @field:ApiModelProperty(
        value = "일정 일시",
        example = "2022-01-01 12:00:00",
        required = false
    )
    @field:NotBlank
    // TODO: 2022/06/03
    var schedule: String? = null,

    var createAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) {
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷이 맞지 않습니다.")
    fun validSchedule(): Boolean {
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch (e: Exception) {
            false
        }
    }
}

fun TodoDto.convertTodoDto(todo: Todo): TodoDto {
    return TodoDto().apply {
        this.id = todo.id
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}