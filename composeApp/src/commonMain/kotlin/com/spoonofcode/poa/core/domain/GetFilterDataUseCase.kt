package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.FilterData
import com.spoonofcode.poa.core.data.repository.FilterRepository
import kotlinx.datetime.LocalDateTime

class GetFilterDataUseCase(
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(
        selectedClubId: Int? = null,
        selectedCoachId: Int? = null,
        selectedLevelId: Int? = null,
        selectedTypeId: Int? = null,
        startDateTime: LocalDateTime? = null,
        endDateTime: LocalDateTime? = null,
    ): FilterData {
        return filterRepository.getSelectedFilters()
    }
}