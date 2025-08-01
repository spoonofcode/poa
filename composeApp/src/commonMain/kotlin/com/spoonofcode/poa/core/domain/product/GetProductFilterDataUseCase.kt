package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductFilterData
import com.spoonofcode.poa.core.data.repository.ProductFilterRepository
import kotlinx.datetime.LocalDateTime

class GetProductFilterDataUseCase(
    private val productFilterRepository: ProductFilterRepository,
) {
    suspend operator fun invoke(
        selectedClubId: Int? = null,
        selectedCoachId: Int? = null,
        selectedLevelId: Int? = null,
        selectedTypeId: Int? = null,
        startDateTime: LocalDateTime? = null,
        endDateTime: LocalDateTime? = null,
    ): ProductFilterData {
        return productFilterRepository.getSelectedFilters()
    }
}