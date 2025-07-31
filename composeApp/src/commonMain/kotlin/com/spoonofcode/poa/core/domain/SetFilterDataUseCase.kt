package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterData
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository

class SetFilterDataUseCase(
    private val NFTInfoFilterRepository: NFTInfoFilterRepository,
) {
    suspend operator fun invoke(
        selectedSerieId: String? = null,
        selectedBlockchainId: String? = null,
    ) {
        NFTInfoFilterRepository.setSelectedFilters(
            newNFTInfoFilterData = NFTInfoFilterData(
                selectedSerieId = selectedSerieId,
                selectedBlockchainId = selectedBlockchainId,
            )
        )
    }
}