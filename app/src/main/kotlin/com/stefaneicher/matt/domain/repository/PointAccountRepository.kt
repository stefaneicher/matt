package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.PointAccount
import kotlinx.coroutines.flow.Flow

interface PointAccountRepository {
    fun getPointAccount(childId: String): Flow<PointAccount?>
    suspend fun getOrCreateAccount(childId: String, familyId: String): Result<PointAccount>
}
