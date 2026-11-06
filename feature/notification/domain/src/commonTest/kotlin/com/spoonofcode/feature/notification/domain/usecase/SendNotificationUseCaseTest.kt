package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.domain.di.notificationDomainTestModule
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SendNotificationUseCaseTest : BaseTest() {
    private lateinit var sendNotificationUseCase: SendNotificationUseCase
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        sendNotificationUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke calls repository create with correct parameters`() =
        runTest {
            val title = "Title"
            val text = "Text"
            val link = "https://example.com"
            val topics = listOf("global")
            val deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0)
            val expirationDateTime = LocalDateTime(2023, 1, 2, 10, 0)

            everySuspend {
                notificationRepository.create(
                    title = title,
                    text = text,
                    link = link,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                )
            } returns Result.success(Unit)

            val result =
                sendNotificationUseCase(
                    title = title,
                    text = text,
                    link = link,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                )

            assertTrue(result.isSuccess)
            verifySuspend {
                notificationRepository.create(
                    title = title,
                    text = text,
                    link = link,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                )
            }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val title = "Title"
            val text = "Text"
            val topics = listOf("global")
            val deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0)
            val expirationDateTime = LocalDateTime(2023, 1, 2, 10, 0)
            val exception = Exception("Failed to send")

            everySuspend {
                notificationRepository.create(
                    title = title,
                    text = text,
                    link = null,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                )
            } returns Result.failure(exception)

            val result =
                sendNotificationUseCase(
                    title = title,
                    text = text,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                )

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
        }
}
