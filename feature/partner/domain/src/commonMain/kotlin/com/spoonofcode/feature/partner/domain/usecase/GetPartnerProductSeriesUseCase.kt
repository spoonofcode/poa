package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.ProductSeries
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository

class GetPartnerProductSeriesUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(): Result<List<ProductSeries>> = partnerRepository.getPartnerSeries()
}