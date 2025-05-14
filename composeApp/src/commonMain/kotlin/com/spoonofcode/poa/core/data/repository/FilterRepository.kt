package com.spoonofcode.poa.core.data.repository

data class FilterData(
    val selectedSerieId: String? = null,
    val selectedBlockchainId: String? = null,
)

class FilterRepository {

    private var selectedFilters = FilterData()

    fun setSelectedFilters(newFilterData: FilterData) {
        selectedFilters = newFilterData
    }

    fun getSelectedFilters(): FilterData = selectedFilters

}