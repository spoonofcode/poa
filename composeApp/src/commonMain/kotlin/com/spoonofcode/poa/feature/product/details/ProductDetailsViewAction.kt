package com.spoonofcode.poa.feature.product.details

sealed interface ProductDetailsViewAction {
    data object AddProduct : ProductDetailsViewAction
}