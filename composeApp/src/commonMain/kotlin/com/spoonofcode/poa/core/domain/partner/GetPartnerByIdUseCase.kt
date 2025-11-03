package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.Partner

class GetPartnerByIdUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(
        partnerId: String
    ): Partner = partnerRepository.read(id = partnerId)
}