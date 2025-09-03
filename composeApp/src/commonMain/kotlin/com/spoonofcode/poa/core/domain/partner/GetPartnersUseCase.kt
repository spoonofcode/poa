package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner

class GetPartnersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): List<Partner> = partnerRepository.readAll()
}

