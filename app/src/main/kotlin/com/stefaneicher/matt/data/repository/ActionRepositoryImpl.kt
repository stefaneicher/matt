package com.stefaneicher.matt.data.repository

import com.stefaneicher.matt.data.local.dao.ActionDao
import com.stefaneicher.matt.data.local.entity.ActionEntity
import com.stefaneicher.matt.domain.model.Action
import com.stefaneicher.matt.domain.repository.ActionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActionRepositoryImpl @Inject constructor(
    private val actionDao: ActionDao
) : ActionRepository {

    override fun getActions(familyId: String): Flow<List<Action>> =
        actionDao.getActionsByFamily(familyId).map { list -> list.map { it.toDomain() } }

    override suspend fun createAction(action: Action): Result<Action> = runCatching {
        val entity = ActionEntity.fromDomain(action.copy(id = UUID.randomUUID().toString()))
        actionDao.insertAction(entity)
        entity.toDomain()
    }

    override suspend fun redeemAction(actionId: String, childId: String): Result<Unit> = runCatching {
        // TODO: Implement full point deduction + FamilyEvent creation via Firestore transaction (Phase 2)
        // For now: no-op stub returns success without mutating state
    }

    override suspend fun deleteAction(actionId: String): Result<Unit> = runCatching {
        actionDao.deleteAction(actionId)
    }
}
