package com.stefaneicher.matt.domain.usecase

import com.stefaneicher.matt.domain.repository.ActionRepository
import javax.inject.Inject

class RedeemActionUseCase @Inject constructor(
    private val actionRepository: ActionRepository
) {
    suspend operator fun invoke(actionId: String, childId: String): Result<Unit> {
        return actionRepository.redeemAction(actionId, childId)
    }
}
