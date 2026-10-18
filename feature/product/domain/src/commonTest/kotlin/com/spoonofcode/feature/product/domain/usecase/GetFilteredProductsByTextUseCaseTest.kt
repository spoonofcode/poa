package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.product.data.test.ProductMockData
import com.spoonofcode.feature.product.domain.di.productDomainTestModule
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetFilteredProductsByTextUseCaseTest : BaseTest() {

    private lateinit var getFilteredProductsByTextUseCase: GetFilteredProductsByTextUseCase

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        getFilteredProductsByTextUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns products matching search text ignore case`() = runTest {
        val products = ProductMockData.PRODUCTS
        val result = getFilteredProductsByTextUseCase("product #1", products)

        assertEquals(1, result.size)
        assertEquals(ProductMockData.PRODUCT_1, result.first())
    }

    @Test
    fun `invoke returns empty list when no products match`() = runTest {
        val products = ProductMockData.PRODUCTS
        val result = getFilteredProductsByTextUseCase("nonexisting", products)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke returns all products when search text matches all`() = runTest {
        val products = ProductMockData.PRODUCTS
        val result = getFilteredProductsByTextUseCase("Product", products)

        assertEquals(2, result.size)
    }
}
