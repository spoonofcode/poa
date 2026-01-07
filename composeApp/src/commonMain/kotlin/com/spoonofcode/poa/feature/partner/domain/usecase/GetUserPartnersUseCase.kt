package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository

class GetUserPartnersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): Result<List<Partner>> = partnerRepository.getUserPartners()
}