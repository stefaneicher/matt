package com.stefaneicher.matt.domain.model

import org.junit.Assert.*
import org.junit.Test
import java.time.Instant

class TaskTest {

    @Test
    fun `task with valid points is created successfully`() {
        val task = Task(
            id = "1",
            familyId = "f1",
            createdByParentId = "p1",
            title = "Clean room",
            description = "",
            points = 5
        )
        assertEquals(5, task.points)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `task with points below 1 throws exception`() {
        Task(
            id = "1",
            familyId = "f1",
            createdByParentId = "p1",
            title = "Test",
            description = "",
            points = 0
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `task with points above 10 throws exception`() {
        Task(
            id = "1",
            familyId = "f1",
            createdByParentId = "p1",
            title = "Test",
            description = "",
            points = 11
        )
    }

    @Test
    fun `task boundary points are valid`() {
        val taskMin = Task("1", "f1", "p1", "T", "", 1)
        val taskMax = Task("2", "f1", "p1", "T", "", 10)
        assertEquals(1, taskMin.points)
        assertEquals(10, taskMax.points)
    }
}
