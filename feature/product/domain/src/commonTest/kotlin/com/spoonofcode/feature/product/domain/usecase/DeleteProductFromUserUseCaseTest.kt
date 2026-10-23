package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.domain.model.NotificationType
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

class DeleteProductFromUserUseCaseTest : BaseTest() {
    private lateinit var deleteProductFromUserUseCase: DeleteProductFromUserUseCase
    private lateinit var productRepository: ProductRepository
    private lateinit var firebaseMessagingRepository: FirebaseMessagingRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        firebaseMessagingRepository = getKoin().get()
        deleteProductFromUserUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success without productSerieId`() =
        runTest {
            val productId = "1"
            everySuspend { productRepository.deleteProductFromUser(productId) } returns Result.success(Unit)

            val result = deleteProductFromUserUseCase(productId)

            assertTrue(result.isSuccess)
            verifySuspend { productRepository.deleteProductFromUser(productId) }
        }

    @Test
    fun `invoke returns success and unsubscribes from topic when productSerieId provided`() =
        runTest {
            val productId = "1"
            val serieId = "series_1"
            val topic = NotificationType.SERIE.createTopic(serieId)

            everySuspend { productRepository.deleteProductFromUser(productId) } returns Result.success(Unit)
            everySuspend { firebaseMessagingRepository.unsubscribeFromTopic(topic) } returns Result.success(Unit)

            val result = deleteProductFromUserUseCase(productId, serieId)

            assertTrue(result.isSuccess)
            verifySuspend { productRepository.deleteProductFromUser(productId) }
            verifySuspend { firebaseMessagingRepository.unsubscribeFromTopic(topic) }
        }

    @Test
    fun `invoke returns failure when productRepository fails`() =
        runTest {
            val productId = "1"
            val exception = Exception("Failed to delete product")
            everySuspend { productRepository.deleteProductFromUser(productId) } returns Result.failure(exception)

            val result = deleteProductFromUserUseCase(productId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { productRepository.deleteProductFromUser(productId) }
        }
}
