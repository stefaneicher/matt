package com.stefaneicher.matt.domain.model

import java.time.Instant

data class FamilyEvent(
    val id: String,
    val familyId: String,
    val childId: String,
    val actorId: String, // who triggered the event
    val type: EventType,
    val title: String,
    val description: String,
    val pointsDelta: Int, // positive = earned, negative = spent
    val relatedEntityId: String? = null, // task or action id
    val animationKey: String? = null,    // Lottie animation asset key
    val isPlayed: Boolean = false,       // consumed by event scroll
    val createdAt: Instant = Instant.now()
)
