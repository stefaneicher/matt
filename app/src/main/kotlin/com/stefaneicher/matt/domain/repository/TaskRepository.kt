package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(familyId: String): Flow<List<Task>>
    fun getTasksForChild(familyId: String, childId: String): Flow<List<Task>>
    suspend fun createTask(task: Task): Result<Task>
    suspend fun completeTask(taskId: String, childId: String): Result<Unit>
    suspend fun deleteTask(taskId: String): Result<Unit>
}
