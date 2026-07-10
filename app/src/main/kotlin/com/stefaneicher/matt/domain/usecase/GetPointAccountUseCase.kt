package com.stefaneicher.matt.domain.usecase

import com.stefaneicher.matt.domain.model.PointAccount
import com.stefaneicher.matt.domain.repository.PointAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPointAccountUseCase @Inject constructor(
    private val pointAccountRepository: PointAccountRepository
) {
    operator fun invoke(childId: String): Flow<PointAccount?> {
        return pointAccountRepository.getPointAccount(childId)
    }
}
