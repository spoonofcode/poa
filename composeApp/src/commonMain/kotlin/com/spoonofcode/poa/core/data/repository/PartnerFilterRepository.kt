package com.spoonofcode.poa.core.data.repository

data class PartnerFilterData(
    val selectedName: String? = null,
    val selectedCollectionName: String? = null,
)

class PartnerFilterRepository {

    private var selectedFilters = PartnerFilterData()

    fun setSelectedFilters(productFilterData: PartnerFilterData) {
        selectedFilters = productFilterData
    }

    fun getSelectedFilters(): PartnerFilterData = selectedFilters

}