package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerCategoryRepository
import com.spoonofcode.poa.core.model.PartnerCategory

class GetPartnerCategoriesUseCase(
    private val partnerCategoryRepository: PartnerCategoryRepository,
) {
    suspend operator fun invoke(): List<PartnerCategory> = partnerCategoryRepository.readAll()
}