package com.spoonofcode.poa.feature.product.details

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProductDetailsViewAction: BaseViewAction {
    data object AddProduct : ProductDetailsViewAction
    data object DeleteProduct : ProductDetailsViewAction
    data object EditProduct : ProductDetailsViewAction
    data class InitView(val productId: String) : ProductDetailsViewAction
}