package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository

class UpdateProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        productId: String,
        ownerUserId: String? = null,
        partnerId: String,
        name: String,
        description: String,
        seriesId: String,
        collectionName: String,
        imageLink: String,
        videoLink: String? = null,
        tagId: String? = null,
        websiteLink: String? = null,
        customLink: String? = null,
    ): Result<Unit> =
        productRepository.update(
            productId = productId,
            ownerUserId = ownerUserId,
            partnerId = partnerId,
            name = name,
            description = description,
            seriesId = seriesId,
            collectionName = collectionName,
            imageLink = imageLink,
            videoLink = videoLink,
            tagId = tagId,
            websiteLink = websiteLink,
            customLink = customLink
        )
}