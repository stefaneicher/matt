package com.stefaneicher.matt.data.local.dao

import androidx.room.*
import com.stefaneicher.matt.data.local.entity.FamilyEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FamilyEventDao {
    @Query("SELECT * FROM family_events WHERE familyId = :familyId AND childId = :childId ORDER BY createdAt DESC")
    fun getEvents(familyId: String, childId: String): Flow<List<FamilyEventEntity>>

    @Query("SELECT * FROM family_events WHERE familyId = :familyId AND childId = :childId AND isPlayed = 0 ORDER BY createdAt ASC")
    fun getUnplayedEvents(familyId: String, childId: String): Flow<List<FamilyEventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: FamilyEventEntity)

    @Query("UPDATE family_events SET isPlayed = 1 WHERE id = :eventId")
    suspend fun markEventPlayed(eventId: String)

    @Query("UPDATE family_events SET isPlayed = 1 WHERE familyId = :familyId AND childId = :childId")
    suspend fun markAllEventsPlayed(familyId: String, childId: String)
}
