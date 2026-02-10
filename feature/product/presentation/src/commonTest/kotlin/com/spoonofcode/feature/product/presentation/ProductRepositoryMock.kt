package com.spoonofcode.feature.product.presentation

import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.feature.product.presentation.ProductMockData.PRODUCTS
import com.spoonofcode.feature.product.presentation.ProductMockData.PRODUCT_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun productRepositoryMock() = mock<ProductRepository> {
    everySuspend { getProduct(any()) } returns Result.success(PRODUCT_1)
    everySuspend { getProductByTagId(any()) } returns Result.success(PRODUCT_1)
    everySuspend { getUserProducts() } returns Result.success(PRODUCTS)
    everySuspend { getProductSeriesIds() } returns
            Result.success(PRODUCTS.map { product -> product.seriesId })
    everySuspend { addProductToUser(any()) } returns Result.success(Unit)
    everySuspend { deleteProductFromUser(any()) } returns Result.success(Unit)
    everySuspend {
        update(
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any()
        )
    } returns Result.success(Unit)
    everySuspend { getProductByTagId(any()) } returns Result.success(PRODUCT_1)
}