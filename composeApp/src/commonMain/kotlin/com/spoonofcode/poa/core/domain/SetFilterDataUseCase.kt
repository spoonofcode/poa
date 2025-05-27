package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.FilterData
import com.spoonofcode.poa.core.data.repository.FilterRepository
import kotlinx.datetime.LocalDateTime

class SetFilterDataUseCase(
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(
        selectedSerieId: String? = null,
        selectedBlockchainId: String? = null,
    ) {
        filterRepository.setSelectedFilters(
            newFilterData = FilterData(
                selectedSerieId = selectedSerieId,
                selectedBlockchainId = selectedBlockchainId,
            )
        )
    }
}