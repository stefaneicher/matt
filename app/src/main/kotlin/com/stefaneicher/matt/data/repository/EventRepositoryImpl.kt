package com.stefaneicher.matt.data.repository

import com.stefaneicher.matt.data.local.dao.FamilyEventDao
import com.stefaneicher.matt.domain.model.FamilyEvent
import com.stefaneicher.matt.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepositoryImpl @Inject constructor(
    private val eventDao: FamilyEventDao
) : EventRepository {

    override fun getEvents(familyId: String, childId: String): Flow<List<FamilyEvent>> =
        eventDao.getEvents(familyId, childId).map { list -> list.map { it.toDomain() } }

    override fun getUnplayedEvents(familyId: String, childId: String): Flow<List<FamilyEvent>> =
        eventDao.getUnplayedEvents(familyId, childId).map { list -> list.map { it.toDomain() } }

    override suspend fun markEventPlayed(eventId: String): Result<Unit> = runCatching {
        eventDao.markEventPlayed(eventId)
    }

    override suspend fun markAllEventsPlayed(familyId: String, childId: String): Result<Unit> = runCatching {
        eventDao.markAllEventsPlayed(familyId, childId)
    }
}
