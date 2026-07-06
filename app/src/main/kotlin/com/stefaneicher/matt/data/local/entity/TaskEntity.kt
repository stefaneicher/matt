package com.stefaneicher.matt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefaneicher.matt.domain.model.Task
import java.time.Instant

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val familyId: String,
    val createdByParentId: String,
    val title: String,
    val description: String,
    val points: Int,
    val isCompleted: Boolean,
    val assignedChildId: String?,
    val completedAt: Long?,
    val createdAt: Long
) {
    fun toDomain() = Task(
        id = id,
        familyId = familyId,
        createdByParentId = createdByParentId,
        title = title,
        description = description,
        points = points,
        isCompleted = isCompleted,
        assignedChildId = assignedChildId,
        completedAt = completedAt?.let { Instant.ofEpochMilli(it) },
        createdAt = Instant.ofEpochMilli(createdAt)
    )

    companion object {
        fun fromDomain(task: Task) = TaskEntity(
            id = task.id,
            familyId = task.familyId,
            createdByParentId = task.createdByParentId,
            title = task.title,
            description = task.description,
            points = task.points,
            isCompleted = task.isCompleted,
            assignedChildId = task.assignedChildId,
            completedAt = task.completedAt?.toEpochMilli(),
            createdAt = task.createdAt.toEpochMilli()
        )
    }
}
