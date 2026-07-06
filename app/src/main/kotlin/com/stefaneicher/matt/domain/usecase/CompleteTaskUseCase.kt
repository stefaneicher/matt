package com.stefaneicher.matt.domain.usecase

import com.stefaneicher.matt.domain.repository.EventRepository
import com.stefaneicher.matt.domain.repository.PointAccountRepository
import com.stefaneicher.matt.domain.repository.TaskRepository
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val pointAccountRepository: PointAccountRepository,
    private val eventRepository: EventRepository
) {
    suspend operator fun invoke(taskId: String, childId: String): Result<Unit> {
        return taskRepository.completeTask(taskId, childId)
    }
}
