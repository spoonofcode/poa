package com.spoonofcode.poa.feature.product.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProductOverviewViewAction : BaseViewAction {
    data class ChangeSearchText(val searchText: String) : ProductOverviewViewAction
    data object InitView : ProductOverviewViewAction
    data class SelectProduct(val productId: String) : ProductOverviewViewAction
}