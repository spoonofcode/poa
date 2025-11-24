package com.spoonofcode.poa.feature.product.details

sealed interface ProductDetailsViewAction {
    data class InitView(val productId: String) : ProductDetailsViewAction
    data object AddProduct : ProductDetailsViewAction
    data object DeleteProduct : ProductDetailsViewAction
    data object EditProduct : ProductDetailsViewAction
}