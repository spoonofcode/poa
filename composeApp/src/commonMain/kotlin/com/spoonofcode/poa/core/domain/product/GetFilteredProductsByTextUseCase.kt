package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.model.Product

class GetFilteredProductsByTextUseCase {
    operator fun invoke(
        searchText: String,
        products: List<Product>,
    ): List<Product> = products.filter { product ->
        product.name.contains(searchText, ignoreCase = true)
    }
}

