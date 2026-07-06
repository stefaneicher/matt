package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.Family
import com.stefaneicher.matt.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FamilyRepository {
    fun getFamily(familyId: String): Flow<Family?>
    fun getFamilyMembers(familyId: String): Flow<List<User>>
    suspend fun createFamily(name: String, parentId: String): Result<Family>
    suspend fun joinFamily(familyId: String, userId: String): Result<Unit>
    suspend fun addChildToFamily(familyId: String, childId: String): Result<Unit>
}
