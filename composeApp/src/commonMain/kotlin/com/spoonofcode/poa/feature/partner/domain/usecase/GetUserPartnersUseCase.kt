package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetUserPartnersUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Partner>> = userDataSource.readUserPartners(
        userId = sessionManager.getSessionUserId(),
    )
}