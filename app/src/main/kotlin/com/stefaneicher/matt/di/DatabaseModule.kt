package com.stefaneicher.matt.di

import android.content.Context
import androidx.room.Room
import com.stefaneicher.matt.data.local.MattDatabase
import com.stefaneicher.matt.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MattDatabase =
        Room.databaseBuilder(context, MattDatabase::class.java, "matt_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideTaskDao(db: MattDatabase): TaskDao = db.taskDao()
    @Provides fun provideActionDao(db: MattDatabase): ActionDao = db.actionDao()
    @Provides fun provideFamilyEventDao(db: MattDatabase): FamilyEventDao = db.familyEventDao()
    @Provides fun providePointAccountDao(db: MattDatabase): PointAccountDao = db.pointAccountDao()
}
