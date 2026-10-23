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

class GetUserProductsUseCaseTest : BaseTest() {
    private lateinit var getUserProductsUseCase: GetUserProductsUseCase
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        getUserProductsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with user products`() =
        runTest {
            val result = getUserProductsUseCase()

            assertTrue(result.isSuccess)
            assertEquals(ProductMockData.PRODUCTS, result.getOrNull())
            verifySuspend { productRepository.getUserProducts() }
        }

    @Test
    fun `invoke returns failure when repository throws`() =
        runTest {
            val exception = Exception("Error getting user products")
            everySuspend { productRepository.getUserProducts() } returns Result.failure(exception)

            val result = getUserProductsUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { productRepository.getUserProducts() }
        }
}
