package com.spoonofcode.feature.product.presentation.overview

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductOverviewViewAction : BaseViewAction {
    data class ChangeSearchText(val searchText: String) : ProductOverviewViewAction
    data object InitView : ProductOverviewViewAction
    data class SelectProduct(val productId: String) : ProductOverviewViewAction
}