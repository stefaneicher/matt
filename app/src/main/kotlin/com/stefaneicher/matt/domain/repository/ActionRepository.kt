package com.stefaneicher.matt.domain.repository

import com.stefaneicher.matt.domain.model.Action
import kotlinx.coroutines.flow.Flow

interface ActionRepository {
    fun getActions(familyId: String): Flow<List<Action>>
    suspend fun createAction(action: Action): Result<Action>
    suspend fun redeemAction(actionId: String, childId: String): Result<Unit>
    suspend fun deleteAction(actionId: String): Result<Unit>
}
