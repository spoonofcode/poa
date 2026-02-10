package com.spoonofcode.feature.product.presentation.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductDetailsViewAction : BaseViewAction {
    data object AddProductToUser : ProductDetailsViewAction
    data object DeleteProductFromUser : ProductDetailsViewAction
    data object EditProduct : ProductDetailsViewAction
    data class InitView(val productId: String) : ProductDetailsViewAction
}