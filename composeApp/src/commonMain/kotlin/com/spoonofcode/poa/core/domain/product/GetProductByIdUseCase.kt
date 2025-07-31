package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.User
import kotlin.time.ExperimentalTime

// TODO Fix this use case
class GetProductByIdUseCase(
    private val productRepository: ProductRepository,
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        productId: String
    ): Product = Product(
        id = 1,
        name = "Product 1",
        description = "Description product 1",
        tagId = "04A971E2E51090",
        collectionName = "Collection 1",
        websiteLink = "https://beautysaute.pl/",
        customLink = "https://beautysaute.pl/",
        ownerUser = User(
            id = 1,
            firstName = "Bartosz",
            lastName = "Luczak",
            email = "bartosz.luczak@gmail.com"
        ),
        )
//        productRepository.read(id = productId)
}