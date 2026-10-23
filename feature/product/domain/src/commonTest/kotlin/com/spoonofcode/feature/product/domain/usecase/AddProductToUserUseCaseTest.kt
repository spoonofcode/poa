package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
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

class AddProductToUserUseCaseTest : BaseTest() {
    private lateinit var addProductToUserUseCase: AddProductToUserUseCase
    private lateinit var productRepository: ProductRepository
    private lateinit var notificationRepository: NotificationRepository
    private lateinit var firebaseMessagingRepository: FirebaseMessagingRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(productDomainTestModule)
        super.beforeTest()
        productRepository = getKoin().get()
        notificationRepository = getKoin().get()
        firebaseMessagingRepository = getKoin().get()
        addProductToUserUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success without productSerieId`() =
        runTest {
            val productId = "1"
            everySuspend { productRepository.addProductToUser(productId) } returns Result.success(Unit)

            val result = addProductToUserUseCase(productId)

            assertTrue(result.isSuccess)
            verifySuspend { productRepository.addProductToUser(productId) }
        }

    @Test
    fun `invoke returns success and subscribes to topic when productSerieId provided and notifications enabled`() =
        runTest {
            val productId = "1"
            val serieId = "series_1"
            val topic = NotificationType.SERIE.createTopic(serieId)

            everySuspend { productRepository.addProductToUser(productId) } returns Result.success(Unit)
            everySuspend { notificationRepository.getNotificationSettings() } returns
                Result.success(
                    NotificationSettings(isNotificationsMyProductSeriesEnabled = true),
                )
            everySuspend { firebaseMessagingRepository.subscribeToTopic(topic) } returns Result.success(Unit)

            val result = addProductToUserUseCase(productId, serieId)

            assertTrue(result.isSuccess)
            verifySuspend { productRepository.addProductToUser(productId) }
            verifySuspend { firebaseMessagingRepository.subscribeToTopic(topic) }
        }

    @Test
    fun `invoke returns success and does not subscribe when notifications disabled`() =
        runTest {
            val productId = "1"
            val serieId = "series_1"

            everySuspend { productRepository.addProductToUser(productId) } returns Result.success(Unit)
            everySuspend { notificationRepository.getNotificationSettings() } returns
                Result.success(
                    NotificationSettings(isNotificationsMyProductSeriesEnabled = false),
                )

            val result = addProductToUserUseCase(productId, serieId)

            assertTrue(result.isSuccess)
            verifySuspend { productRepository.addProductToUser(productId) }
        }

    @Test
    fun `invoke returns failure when productRepository fails`() =
        runTest {
            val productId = "1"
            val exception = Exception("Failed to add product")
            everySuspend { productRepository.addProductToUser(productId) } returns Result.failure(exception)

            val result = addProductToUserUseCase(productId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { productRepository.addProductToUser(productId) }
        }
}
