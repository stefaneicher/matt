package com.stefaneicher.matt.data.repository

import com.stefaneicher.matt.data.local.dao.PointAccountDao
import com.stefaneicher.matt.data.local.entity.PointAccountEntity
import com.stefaneicher.matt.domain.model.PointAccount
import com.stefaneicher.matt.domain.repository.PointAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointAccountRepositoryImpl @Inject constructor(
    private val pointAccountDao: PointAccountDao
) : PointAccountRepository {

    override fun getPointAccount(childId: String): Flow<PointAccount?> =
        pointAccountDao.getPointAccount(childId).map { it?.toDomain() }

    override suspend fun getOrCreateAccount(childId: String, familyId: String): Result<PointAccount> = runCatching {
        val existing = pointAccountDao.getPointAccountOnce(childId)
        if (existing != null) return@runCatching existing.toDomain()
        val newAccount = PointAccountEntity(
            id = UUID.randomUUID().toString(),
            childId = childId,
            familyId = familyId,
            balance = 0,
            totalEarned = 0,
            totalSpent = 0
        )
        pointAccountDao.insertOrUpdate(newAccount)
        newAccount.toDomain()
    }
}
