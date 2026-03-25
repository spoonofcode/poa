package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.ProductSeries
import com.spoonofcode.feature.product.domain.repository.ProductSeriesRepository

class GetPartnerProductSeriesUseCase(
    private val productSeriesRepository: ProductSeriesRepository,
) {
    suspend operator fun invoke(): Result<List<ProductSeries>> = productSeriesRepository.getAllPartnerProductSeries()
}