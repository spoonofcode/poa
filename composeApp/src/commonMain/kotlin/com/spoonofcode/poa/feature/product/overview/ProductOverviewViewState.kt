package com.spoonofcode.poa.feature.product.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProductOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val searchText: String = "",
    val initProducts: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
) : BaseViewState(screenState)