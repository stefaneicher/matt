package com.stefaneicher.matt.data.local.dao

import androidx.room.*
import com.stefaneicher.matt.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE familyId = :familyId ORDER BY createdAt DESC")
    fun getTasksByFamily(familyId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE familyId = :familyId AND (assignedChildId = :childId OR assignedChildId IS NULL) ORDER BY createdAt DESC")
    fun getTasksForChild(familyId: String, childId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: String): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTask(id: String)
}
