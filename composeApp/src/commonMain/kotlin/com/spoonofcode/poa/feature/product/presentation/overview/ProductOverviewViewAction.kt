package com.spoonofcode.poa.feature.product.presentation.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProductOverviewViewAction : BaseViewAction {
    data class ChangeSearchText(val searchText: String) :
        com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction
    data object InitView :
        com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction
    data class SelectProduct(val productId: String) :
        com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction
}