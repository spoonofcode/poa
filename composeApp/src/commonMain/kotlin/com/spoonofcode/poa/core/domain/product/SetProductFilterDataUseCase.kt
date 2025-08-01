package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductFilterData
import com.spoonofcode.poa.core.data.repository.ProductFilterRepository

class SetProductFilterDataUseCase(
    private val productFilterRepository: ProductFilterRepository,
) {
    suspend operator fun invoke(
        selectedName: String? = null,
        selectedCollectionName: String? = null,
    ) {
        productFilterRepository.setSelectedFilters(
            productFilterData = ProductFilterData(
                selectedName = selectedName,
                selectedCollectionName = selectedCollectionName,
            ),
        )
    }
}