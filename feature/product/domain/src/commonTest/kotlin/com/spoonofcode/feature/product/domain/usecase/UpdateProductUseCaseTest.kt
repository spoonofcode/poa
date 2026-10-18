package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
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

class UpdateProductUseCaseTest : BaseTest() {

    private lateinit var updateProductUseCase: UpdateProductUseCase
    private lateinit var productRepository: ProductRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        updateProductUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when productRepository updates successfully`() = runTest {
        val productId = "1"
        val customLink = "https://custom.link"
        everySuspend { productRepository.update(productId, customLink) } returns Result.success(Unit)

        val result = updateProductUseCase(productId, customLink)

        assertTrue(result.isSuccess)
        verifySuspend { productRepository.update(productId, customLink) }
    }

    @Test
    fun `invoke returns failure when productRepository fails`() = runTest {
        val productId = "1"
        val customLink = "https://custom.link"
        val exception = Exception("Update failed")
        everySuspend { productRepository.update(productId, customLink) } returns Result.failure(exception)

        val result = updateProductUseCase(productId, customLink)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { productRepository.update(productId, customLink) }
    }
}
