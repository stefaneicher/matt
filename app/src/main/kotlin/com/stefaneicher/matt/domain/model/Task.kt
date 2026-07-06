package com.stefaneicher.matt.domain.model

import java.time.Instant

data class Task(
    val id: String,
    val familyId: String,
    val createdByParentId: String,
    val title: String,
    val description: String,
    val points: Int, // 1–10
    val isCompleted: Boolean = false,
    val assignedChildId: String? = null,
    val completedAt: Instant? = null,
    val createdAt: Instant = Instant.now()
) {
    init {
        require(points in 1..10) { "Task points must be between 1 and 10" }
    }
}
