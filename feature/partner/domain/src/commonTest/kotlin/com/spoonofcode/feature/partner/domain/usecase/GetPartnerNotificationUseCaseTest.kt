package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.partner.domain.di.partnerDomainTestModule
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
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

class GetPartnerNotificationUseCaseTest : BaseTest() {
    private lateinit var getPartnerNotificationUseCase: GetPartnerNotificationUseCase
    private lateinit var notificationRepository: PartnerNotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        getPartnerNotificationUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns notification from repository`() =
        runTest {
            val notificationId = "1"
            val notification =
                PartnerNotification(
                    id = notificationId,
                    title = "Title",
                    text = "Text",
                    deliveryDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = listOf("global"),
                    ownerId = "owner",
                )
            everySuspend { notificationRepository.getNotification(notificationId) } returns Result.success(notification)

            val result = getPartnerNotificationUseCase(notificationId)

            assertTrue(result.isSuccess)
            assertEquals(notification, result.getOrNull())
            verifySuspend { notificationRepository.getNotification(notificationId) }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val notificationId = "1"
            val exception = Exception("Notification not found")
            everySuspend { notificationRepository.getNotification(notificationId) } returns Result.failure(exception)

            val result = getPartnerNotificationUseCase(notificationId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { notificationRepository.getNotification(notificationId) }
        }
}
