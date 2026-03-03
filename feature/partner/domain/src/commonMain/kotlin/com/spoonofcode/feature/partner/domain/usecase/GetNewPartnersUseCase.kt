package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

// TODO #138 Fix GetNewPartnersUseCase
class GetNewPartnersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(limit: Int = 3): Result<List<Partner>> {
        return partnerRepository.getPartners().map { partners ->
            partners
                .filter { !it.imageLink.isNullOrBlank() }
                .take(limit)
        }
    }
}

