package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnerUseCase(
    private val partnerRepository: PartnerRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Result<Partner> =
        partnerRepository.getPartner(sessionRepository.getSessionUserId())
}

