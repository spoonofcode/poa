package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository

class GetPartnerCategoriesUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): Result<List<PartnerCategory>> =
        partnerRepository.getCategories()
}