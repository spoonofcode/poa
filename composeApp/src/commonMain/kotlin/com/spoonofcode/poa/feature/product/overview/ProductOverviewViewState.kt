package com.spoonofcode.poa.feature.product.overview

import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProductOverviewViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val searchText: String = "",
    val initProducts: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
) : BaseViewState()