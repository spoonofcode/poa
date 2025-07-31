package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterData
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import kotlinx.datetime.LocalDateTime

class GetFilterDataUseCase(
    private val NFTInfoFilterRepository: NFTInfoFilterRepository,
) {
    suspend operator fun invoke(
        selectedClubId: Int? = null,
        selectedCoachId: Int? = null,
        selectedLevelId: Int? = null,
        selectedTypeId: Int? = null,
        startDateTime: LocalDateTime? = null,
        endDateTime: LocalDateTime? = null,
    ): NFTInfoFilterData {
        return NFTInfoFilterRepository.getSelectedFilters()
    }
}