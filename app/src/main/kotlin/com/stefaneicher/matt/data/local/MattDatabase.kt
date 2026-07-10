package com.stefaneicher.matt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stefaneicher.matt.data.local.dao.*
import com.stefaneicher.matt.data.local.entity.*

@Database(
    entities = [
        TaskEntity::class,
        ActionEntity::class,
        FamilyEventEntity::class,
        PointAccountEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class MattDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun actionDao(): ActionDao
    abstract fun familyEventDao(): FamilyEventDao
    abstract fun pointAccountDao(): PointAccountDao
}
