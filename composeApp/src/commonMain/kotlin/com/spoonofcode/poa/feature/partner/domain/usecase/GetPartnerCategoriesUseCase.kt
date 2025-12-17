package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.model.PartnerCategory

class GetPartnerCategoriesUseCase(
    private val partnerCategoryRepository: PartnerCategoryRepository,
) {
    suspend operator fun invoke(): Result<List<PartnerCategory>> = partnerCategoryRepository.readAll()
}