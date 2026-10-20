package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
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

class UpdatePartnerNotificationUseCaseTest : BaseTest() {

    private lateinit var updatePartnerNotificationUseCase: UpdatePartnerNotificationUseCase
    private lateinit var notificationRepository: PartnerNotificationRepository
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        sessionRepository = getKoin().get()
        updatePartnerNotificationUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke calls repository updateNotification with correct parameters`() = runTest {
        val notificationId = "1"
        val title = "Updated Title"
        val text = "Updated Text"
        val link = "https://example.com"
        val topics = listOf("global")
        val deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0)
        val expirationDateTime = LocalDateTime(2023, 1, 2, 10, 0)
        val ownerId = "owner123"

        everySuspend { sessionRepository.getSessionUserId() } returns ownerId
        everySuspend {
            notificationRepository.updateNotification(
                notificationId = notificationId,
                title = title,
                text = text,
                link = link,
                topics = topics,
                deliveryDateTime = deliveryDateTime,
                expirationDateTime = expirationDateTime,
                ownerId = ownerId,
            )
        } returns Result.success(Unit)

        val result = updatePartnerNotificationUseCase(
            notificationId = notificationId,
            title = title,
            text = text,
            link = link,
            topics = topics,
            deliveryDateTime = deliveryDateTime,
            expirationDateTime = expirationDateTime,
        )

        assertTrue(result.isSuccess)
        verifySuspend {
            notificationRepository.updateNotification(
                notificationId = notificationId,
                title = title,
                text = text,
                link = link,
                topics = topics,
                deliveryDateTime = deliveryDateTime,
                expirationDateTime = expirationDateTime,
                ownerId = ownerId,
            )
        }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val notificationId = "1"
        val title = "Updated Title"
        val text = "Updated Text"
        val topics = listOf("global")
        val deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0)
        val expirationDateTime = LocalDateTime(2023, 1, 2, 10, 0)
        val ownerId = "owner123"
        val exception = Exception("Update failed")

        everySuspend { sessionRepository.getSessionUserId() } returns ownerId
        everySuspend {
            notificationRepository.updateNotification(
                notificationId = notificationId,
                title = title,
                text = text,
                link = null,
                topics = topics,
                deliveryDateTime = deliveryDateTime,
                expirationDateTime = expirationDateTime,
                ownerId = ownerId,
            )
        } returns Result.failure(exception)

        val result = updatePartnerNotificationUseCase(
            notificationId = notificationId,
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
