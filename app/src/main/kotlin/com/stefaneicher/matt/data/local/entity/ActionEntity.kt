package com.stefaneicher.matt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefaneicher.matt.domain.model.Action
import java.time.Instant

@Entity(tableName = "actions")
data class ActionEntity(
    @PrimaryKey val id: String,
    val familyId: String,
    val createdByParentId: String,
    val title: String,
    val description: String,
    val pointCost: Int,
    val createdAt: Long
) {
    fun toDomain() = Action(
        id = id,
        familyId = familyId,
        createdByParentId = createdByParentId,
        title = title,
        description = description,
        pointCost = pointCost,
        createdAt = Instant.ofEpochMilli(createdAt)
    )

    companion object {
        fun fromDomain(action: Action) = ActionEntity(
            id = action.id,
            familyId = action.familyId,
            createdByParentId = action.createdByParentId,
            title = action.title,
            description = action.description,
            pointCost = action.pointCost,
            createdAt = action.createdAt.toEpochMilli()
        )
    }
}
