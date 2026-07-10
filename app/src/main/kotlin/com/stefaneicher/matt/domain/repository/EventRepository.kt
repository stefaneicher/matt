package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.FamilyEvent
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(familyId: String, childId: String): Flow<List<FamilyEvent>>
    fun getUnplayedEvents(familyId: String, childId: String): Flow<List<FamilyEvent>>
    suspend fun markEventPlayed(eventId: String): Result<Unit>
    suspend fun markAllEventsPlayed(familyId: String, childId: String): Result<Unit>
}
