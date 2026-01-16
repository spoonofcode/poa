package com.spoonofcode.poa.feature.product.presentation.details

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface ProductDetailsViewAction : BaseViewAction {
    data object AddProductToUser : ProductDetailsViewAction
    data object DeleteProductFromUser : ProductDetailsViewAction
    data object EditProduct : ProductDetailsViewAction
    data class InitView(val productId: String) : ProductDetailsViewAction
}