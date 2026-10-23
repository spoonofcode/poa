package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.product.data.test.ProductMockData
import com.spoonofcode.feature.product.domain.di.productDomainTestModule
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetProductByIdUseCaseTest : BaseTest() {
    private lateinit var getProductByIdUseCase: GetProductByIdUseCase
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        getProductByIdUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with product`() =
        runTest {
            val productId = ProductMockData.PRODUCT_1.id
            val result = getProductByIdUseCase(productId)

            assertTrue(result.isSuccess)
            assertEquals(ProductMockData.PRODUCT_1, result.getOrNull())
            verifySuspend { productRepository.getProduct(productId) }
        }

    @Test
    fun `invoke returns failure when repository throws`() =
        runTest {
            val productId = "invalid_id"
            val exception = Exception("Product not found")
            everySuspend { productRepository.getProduct(productId) } returns Result.failure(exception)

            val result = getProductByIdUseCase(productId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { productRepository.getProduct(productId) }
        }
}
