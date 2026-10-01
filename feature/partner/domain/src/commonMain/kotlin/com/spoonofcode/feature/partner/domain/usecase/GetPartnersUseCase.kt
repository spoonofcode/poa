package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): Result<List<Partner>> = partnerRepository.getPartners()
        .map { partners ->
            partners.filterNot {it.imageLink.isNullOrEmpty()}
        }
}

