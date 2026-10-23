package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.data.test.NotificationMockData
import com.spoonofcode.feature.notification.domain.di.notificationDomainTestModule
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetNotificationByIdUseCaseTest : BaseTest() {
    private lateinit var getNotificationByIdUseCase: GetNotificationByIdUseCase
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        getNotificationByIdUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with notification`() =
        runTest {
            val id = NotificationMockData.NOTIFICATION_1.id
            val result = getNotificationByIdUseCase(id)

            assertTrue(result.isSuccess)
            assertEquals(NotificationMockData.NOTIFICATION_1, result.getOrNull())
            verifySuspend { notificationRepository.read(id) }
        }

    @Test
    fun `invoke returns failure when repository throws`() =
        runTest {
            val id = "invalid_id"
            val exception = Exception("Notification not found")
            everySuspend { notificationRepository.read(id) } returns Result.failure(exception)

            val result = getNotificationByIdUseCase(id)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { notificationRepository.read(id) }
        }
}
