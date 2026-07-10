package com.stefaneicher.matt.domain.usecase

import com.stefaneicher.matt.domain.model.FamilyEvent
import com.stefaneicher.matt.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnplayedEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    operator fun invoke(familyId: String, childId: String): Flow<List<FamilyEvent>> {
        return eventRepository.getUnplayedEvents(familyId, childId)
    }
}
