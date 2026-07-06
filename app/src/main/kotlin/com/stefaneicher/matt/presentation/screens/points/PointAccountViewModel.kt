package com.stefaneicher.matt.presentation.screens.points

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefaneicher.matt.domain.model.PointAccount
import com.stefaneicher.matt.domain.usecase.GetPointAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PointAccountViewModel @Inject constructor(
    private val getPointAccountUseCase: GetPointAccountUseCase
) : ViewModel() {

    // TODO: Replace with actual child ID from session
    private val childId = "demo_child"

    val account: StateFlow<PointAccount?> = getPointAccountUseCase(childId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}
