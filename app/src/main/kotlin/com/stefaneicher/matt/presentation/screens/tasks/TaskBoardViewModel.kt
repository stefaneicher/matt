package com.stefaneicher.matt.presentation.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefaneicher.matt.domain.model.Task
import com.stefaneicher.matt.domain.repository.TaskRepository
import com.stefaneicher.matt.domain.usecase.CompleteTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val completeTaskUseCase: CompleteTaskUseCase
) : ViewModel() {

    // TODO: Replace hardcoded IDs with actual session data from AuthRepository
    private val familyId = "demo_family"
    private val childId = "demo_child"

    val tasks: StateFlow<List<Task>> = taskRepository
        .getTasks(familyId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onCompleteTask(taskId: String) {
        viewModelScope.launch {
            completeTaskUseCase(taskId, childId)
        }
    }

    fun onAddTask() {
        viewModelScope.launch {
            // Stub: in real app, show a dialog/sheet to enter task details
            taskRepository.createTask(
                Task(
                    id = UUID.randomUUID().toString(),
                    familyId = familyId,
                    createdByParentId = "demo_parent",
                    title = "Neue Aufgabe",
                    description = "Beschreibung",
                    points = 5,
                    createdAt = Instant.now()
                )
            )
        }
    }
}
