package com.spoonofcode.feature.product.presentation.details

import com.spoonofcode.core.presentation.base.BaseViewAction

internal sealed interface ProductDetailsViewAction : BaseViewAction {
    data object AddProductToProfile : ProductDetailsViewAction
    data object DeleteProductFromProfile : ProductDetailsViewAction
    data object EditProduct : ProductDetailsViewAction
    data class InitView(val productId: String) : ProductDetailsViewAction
    data object ConfirmDeleteProductFromProfile : ProductDetailsViewAction
    data object CancelDeleteProductFromProfile : ProductDetailsViewAction
}