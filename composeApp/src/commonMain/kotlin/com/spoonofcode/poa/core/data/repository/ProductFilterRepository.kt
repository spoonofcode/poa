package com.spoonofcode.poa.core.data.repository

data class ProductFilterData(
    val selectedName: String? = null,
    val selectedCollectionName: String? = null,
)

class ProductFilterRepository {

    private var selectedFilters = ProductFilterData()

    fun setSelectedFilters(productFilterData: ProductFilterData) {
        selectedFilters = productFilterData
    }

    fun getSelectedFilters(): ProductFilterData = selectedFilters

}