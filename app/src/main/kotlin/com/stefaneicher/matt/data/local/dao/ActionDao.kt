package com.stefaneicher.matt.data.local.dao

import androidx.room.*
import com.stefaneicher.matt.data.local.entity.ActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {
    @Query("SELECT * FROM actions WHERE familyId = :familyId ORDER BY createdAt DESC")
    fun getActionsByFamily(familyId: String): Flow<List<ActionEntity>>

    @Query("SELECT * FROM actions WHERE id = :id")
    suspend fun getActionById(id: String): ActionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAction(action: ActionEntity)

    @Query("DELETE FROM actions WHERE id = :id")
    suspend fun deleteAction(id: String)
}
