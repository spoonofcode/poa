package com.spoonofcode.feature.product.presentation.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.poa.feature.product.domain.model.Product

@Immutable
internal data class ProductOverviewViewState(
    val searchText: String = "",
    val initProducts: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
) : BaseViewState()