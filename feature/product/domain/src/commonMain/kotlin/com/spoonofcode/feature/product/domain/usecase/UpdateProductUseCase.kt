package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.repository.ProductRepository

class UpdateProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        id: String,
        customLink: String? = null,
        description: String? = null,
        imageLink: String? = null,
        name: String? = null,
        ownerEmail: String? = null,
        partnerId: String? = null,
        seriesId: String? = null,
        seriesName: String? = null,
        videoLink: String? = null,
    ): Result<Unit> =
        productRepository.update(
            productId = id,
            partnerId = partnerId,
            name = name,
            description = description,
            seriesId = seriesId,
            seriesName = seriesName,
            imageLink = imageLink,
            videoLink = videoLink,
            customLink = customLink,
        )
}