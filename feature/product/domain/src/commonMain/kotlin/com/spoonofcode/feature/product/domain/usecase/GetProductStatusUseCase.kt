package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.product.domain.model.ProductStatus

class GetProductStatusUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(
        ownerEmail: String? = null
    ): ProductStatus {
        val userEmail = sessionRepository.getSessionUserEmail()

        return when (ownerEmail) {
            null -> ProductStatus.UNASSIGNED
            userEmail -> ProductStatus.OWNED_BY_SELF
            else -> ProductStatus.OWNED_BY_OTHER
        }
    }
}