package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.User
import com.stefaneicher.matt.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String, name: String, role: UserRole): Result<User>
    suspend fun signOut()
    fun isSignedIn(): Boolean
}
