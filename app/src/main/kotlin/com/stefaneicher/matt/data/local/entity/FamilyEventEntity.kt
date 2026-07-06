package com.stefaneicher.matt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefaneicher.matt.domain.model.EventType
import com.stefaneicher.matt.domain.model.FamilyEvent
import java.time.Instant

@Entity(tableName = "family_events")
data class FamilyEventEntity(
    @PrimaryKey val id: String,
    val familyId: String,
    val childId: String,
    val actorId: String,
    val type: String,
    val title: String,
    val description: String,
    val pointsDelta: Int,
    val relatedEntityId: String?,
    val animationKey: String?,
    val isPlayed: Boolean,
    val createdAt: Long
) {
    fun toDomain() = FamilyEvent(
        id = id,
        familyId = familyId,
        childId = childId,
        actorId = actorId,
        type = EventType.valueOf(type),
        title = title,
        description = description,
        pointsDelta = pointsDelta,
        relatedEntityId = relatedEntityId,
        animationKey = animationKey,
        isPlayed = isPlayed,
        createdAt = Instant.ofEpochMilli(createdAt)
    )

    companion object {
        fun fromDomain(event: FamilyEvent) = FamilyEventEntity(
            id = event.id,
            familyId = event.familyId,
            childId = event.childId,
            actorId = event.actorId,
            type = event.type.name,
            title = event.title,
            description = event.description,
            pointsDelta = event.pointsDelta,
            relatedEntityId = event.relatedEntityId,
            animationKey = event.animationKey,
            isPlayed = event.isPlayed,
            createdAt = event.createdAt.toEpochMilli()
        )
    }
}
