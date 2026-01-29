package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.PartnerUser
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnerUsersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(partnerId: String): Result<List<PartnerUser>> =
        partnerRepository.getPartnerUsers(partnerId = partnerId)
}

