package com.stefaneicher.matt.presentation.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefaneicher.matt.domain.model.FamilyEvent
import com.stefaneicher.matt.domain.repository.EventRepository
import com.stefaneicher.matt.domain.usecase.GetUnplayedEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventScrollViewModel @Inject constructor(
    private val getUnplayedEventsUseCase: GetUnplayedEventsUseCase,
    private val eventRepository: EventRepository
) : ViewModel() {

    private val familyId = "demo_family"
    private val childId = "demo_child"

    val unplayedEvents: StateFlow<List<FamilyEvent>> =
        getUnplayedEventsUseCase(familyId, childId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _currentPlayingEvent = MutableStateFlow<FamilyEvent?>(null)
    val currentPlayingEvent: StateFlow<FamilyEvent?> = _currentPlayingEvent.asStateFlow()

    fun onPlayEvent(event: FamilyEvent) {
        _currentPlayingEvent.value = event
    }

    fun onAnimationComplete() {
        val event = _currentPlayingEvent.value ?: return
        viewModelScope.launch {
            eventRepository.markEventPlayed(event.id)
            _currentPlayingEvent.value = null
        }
    }

    fun onPlayAll() {
        val events = unplayedEvents.value
        if (events.isNotEmpty()) {
            onPlayEvent(events.first())
        }
    }
}
