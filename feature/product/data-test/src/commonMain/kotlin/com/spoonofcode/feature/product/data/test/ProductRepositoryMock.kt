package com.spoonofcode.feature.product.data.test

import com.spoonofcode.feature.product.domain.repository.ProductRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun productRepositoryMock() = mock<ProductRepository> {
    everySuspend { getProduct(any()) } returns Result.success(ProductMockData.PRODUCT_1)
    everySuspend { getUserProducts() } returns Result.success(ProductMockData.PRODUCTS)
    everySuspend { addProductToUser(any()) } returns Result.success(Unit)
    everySuspend { deleteProductFromUser(any()) } returns Result.success(Unit)
    everySuspend {
        updateProductItem(
            any(),
            any(),
        )
    } returns Result.success(Unit)
}