package com.stefaneicher.matt.di

import com.stefaneicher.matt.data.repository.*
import com.stefaneicher.matt.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds @Singleton
    abstract fun bindActionRepository(impl: ActionRepositoryImpl): ActionRepository

    @Binds @Singleton
    abstract fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

    @Binds @Singleton
    abstract fun bindPointAccountRepository(impl: PointAccountRepositoryImpl): PointAccountRepository
}
