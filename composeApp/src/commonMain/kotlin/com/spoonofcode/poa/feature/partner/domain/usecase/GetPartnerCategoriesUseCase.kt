package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.feature.partner.domain.PartnerCategoryDataSource
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory

class GetPartnerCategoriesUseCase(
    private val partnerCategoryDataSource: PartnerCategoryDataSource,
) {
    suspend operator fun invoke(): Result<List<PartnerCategory>> =
        partnerCategoryDataSource.readAll()
}