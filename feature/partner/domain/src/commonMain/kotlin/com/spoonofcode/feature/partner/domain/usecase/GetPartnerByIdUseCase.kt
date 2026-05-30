package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnerByIdUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(
        partnerId: String
    ): Result<Partner> = partnerRepository.getPartner(partnerId = partnerId)
}