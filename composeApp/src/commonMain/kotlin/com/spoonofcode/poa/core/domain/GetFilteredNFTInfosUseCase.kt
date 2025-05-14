package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.FilterRepository
import com.spoonofcode.poa.core.data.repository.NFTInfoRepository
import com.spoonofcode.poa.core.model.NFTInfo
import com.spoonofcode.poa.feature.search.filter.FilterViewState

class GetFilteredNFTInfosUseCase(
    private val nftInfoRepository: NFTInfoRepository,
    private val filterRepository: FilterRepository,
) {
    suspend operator fun invoke(): List<NFTInfo> {
        val nftInfos = nftInfoRepository.readAll()
        val selectedFilter = filterRepository.getSelectedFilters()
        return nftInfos.filter { nftInfo ->
            val matchesSeriesFilter = selectedFilter.selectedSerieId == null ||
                    selectedFilter.selectedSerieId == FilterViewState.ALL_OPTION_NAME ||
                    nftInfo.series == selectedFilter.selectedSerieId
            val matchesBlockchainFilter = selectedFilter.selectedBlockchainId == null ||
                    selectedFilter.selectedBlockchainId == FilterViewState.ALL_OPTION_NAME ||
                    nftInfo.blockchain == selectedFilter.selectedBlockchainId
            matchesSeriesFilter && matchesBlockchainFilter
        }
    }
}

