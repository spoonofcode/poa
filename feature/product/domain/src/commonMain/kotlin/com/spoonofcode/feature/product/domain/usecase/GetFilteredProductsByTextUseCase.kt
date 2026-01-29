package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.Product

class GetFilteredProductsByTextUseCase {
    operator fun invoke(
        searchText: String,
        products: List<Product>,
    ): List<Product> = products.filter { product ->
        product.name.contains(searchText, ignoreCase = true)
    }
}

