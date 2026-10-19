package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.ProductType
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class UpdateProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        id: String,
        productType: ProductType,
        customLink: String? = null,
        redirectLink: String? = null,
    ): Result<Unit> = when (productType) {
        ProductType.ITEM -> productRepository.updateProductItem(
            productId = id,
            customLink = customLink,
        )

        ProductType.REDIRECT -> productRepository.updateProductRedirect(
            productId = id,
            redirectLink = redirectLink,
        )
    }
}