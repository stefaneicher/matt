package com.stefaneicher.matt.data.repository

import com.stefaneicher.matt.data.local.dao.TaskDao
import com.stefaneicher.matt.data.local.entity.TaskEntity
import com.stefaneicher.matt.domain.model.Task
import com.stefaneicher.matt.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getTasks(familyId: String): Flow<List<Task>> =
        taskDao.getTasksByFamily(familyId).map { list -> list.map { it.toDomain() } }

    override fun getTasksForChild(familyId: String, childId: String): Flow<List<Task>> =
        taskDao.getTasksForChild(familyId, childId).map { list -> list.map { it.toDomain() } }

    override suspend fun createTask(task: Task): Result<Task> = runCatching {
        val entity = TaskEntity.fromDomain(task.copy(id = UUID.randomUUID().toString()))
        taskDao.insertTask(entity)
        entity.toDomain()
    }

    override suspend fun completeTask(taskId: String, childId: String): Result<Unit> = runCatching {
        val existing = taskDao.getTaskById(taskId)
            ?: error("Task not found")
        taskDao.updateTask(
            existing.copy(
                isCompleted = true,
                completedAt = Instant.now().toEpochMilli()
            )
        )
    }

    override suspend fun deleteTask(taskId: String): Result<Unit> = runCatching {
        taskDao.deleteTask(taskId)
    }
}
