package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
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
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        sessionRepository = getKoin().get()
        sendNotificationUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke calls repository create with correct parameters including session userId`() =
        runTest {
            val title = "Title"
            val text = "Text"
            val link = "https://example.com"
            val topics = listOf("global")
            val deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0)
            val expirationDateTime = LocalDateTime(2023, 1, 2, 10, 0)
            val userId = "user123"

            everySuspend { sessionRepository.getSessionUserId() } returns userId
            everySuspend {
                notificationRepository.create(
                    title = title,
                    text = text,
                    link = link,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                    ownerId = userId,
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
                    ownerId = userId,
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
            val userId = "user123"
            val exception = Exception("Failed to send")

            everySuspend { sessionRepository.getSessionUserId() } returns userId
            everySuspend {
                notificationRepository.create(
                    title = title,
                    text = text,
                    link = null,
                    topics = topics,
                    deliveryDateTime = deliveryDateTime,
                    expirationDateTime = expirationDateTime,
                    ownerId = userId,
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
