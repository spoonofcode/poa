package com.spoonofcode.poa.feature.product.presentation.details

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface ProductDetailsViewAction: BaseViewAction {
    data object AddProductToUser :
        com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction
    data object DeleteProductFromUser :
        com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction
    data object EditProduct :
        com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction
    data class InitView(val productId: String) :
        com.spoonofcode.poa.feature.product.presentation.details.ProductDetailsViewAction
}