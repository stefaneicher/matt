package com.stefaneicher.matt.domain.usecase

import com.stefaneicher.matt.domain.repository.EventRepository
import com.stefaneicher.matt.domain.repository.PointAccountRepository
import com.stefaneicher.matt.domain.repository.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class CompleteTaskUseCaseTest {

    private val taskRepository = mockk<TaskRepository>()
    private val pointAccountRepository = mockk<PointAccountRepository>()
    private val eventRepository = mockk<EventRepository>()

    private val useCase = CompleteTaskUseCase(taskRepository, pointAccountRepository, eventRepository)

    @Test
    fun `invoke delegates to taskRepository completeTask`() = runTest {
        coEvery { taskRepository.completeTask("task1", "child1") } returns Result.success(Unit)

        val result = useCase("task1", "child1")

        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { taskRepository.completeTask("task1", "child1") }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val error = RuntimeException("Not found")
        coEvery { taskRepository.completeTask(any(), any()) } returns Result.failure(error)

        val result = useCase("bad_task", "child1")

        assertTrue(result.isFailure)
    }
}
