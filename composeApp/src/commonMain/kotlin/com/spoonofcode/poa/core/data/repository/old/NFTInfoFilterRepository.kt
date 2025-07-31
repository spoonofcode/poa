package com.spoonofcode.poa.core.data.repository.old

data class NFTInfoFilterData(
    val selectedSerieId: String? = null,
    val selectedBlockchainId: String? = null,
)

class NFTInfoFilterRepository {

    private var selectedFilters = NFTInfoFilterData()

    fun setSelectedFilters(newNFTInfoFilterData: NFTInfoFilterData) {
        selectedFilters = newNFTInfoFilterData
    }

    fun getSelectedFilters(): NFTInfoFilterData = selectedFilters

}