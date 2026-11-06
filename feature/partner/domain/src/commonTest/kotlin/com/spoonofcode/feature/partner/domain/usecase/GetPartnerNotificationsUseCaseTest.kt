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

class GetPartnerNotificationsUseCaseTest : BaseTest() {
    private lateinit var getPartnerNotificationsUseCase: GetPartnerNotificationsUseCase
    private lateinit var notificationRepository: PartnerNotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(partnerDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        getPartnerNotificationsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns notifications sorted by deliveryDateTime descending`() =
        runTest {
            val notification1 =
                PartnerNotification(
                    id = "1",
                    title = "Title 1",
                    text = "Text 1",
                    deliveryDateTime = LocalDateTime(2023, 1, 1, 10, 0),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = emptyList(),
                    ownerId = "owner",
                )
            val notification2 =
                PartnerNotification(
                    id = "2",
                    title = "Title 2",
                    text = "Text 2",
                    deliveryDateTime = LocalDateTime(2023, 1, 2, 10, 0),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = emptyList(),
                    ownerId = "owner",
                )

            everySuspend { notificationRepository.getNotifications() } returns Result.success(listOf(notification1, notification2))

            val result = getPartnerNotificationsUseCase()

            assertTrue(result.isSuccess)
            val list = result.getOrNull()
            assertEquals(2, list?.size)
            assertEquals(notification2, list?.get(0))
            assertEquals(notification1, list?.get(1))
            verifySuspend { notificationRepository.getNotifications() }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val exception = Exception("Failed to get notifications")
            everySuspend { notificationRepository.getNotifications() } returns Result.failure(exception)

            val result = getPartnerNotificationsUseCase()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { notificationRepository.getNotifications() }
        }
}
