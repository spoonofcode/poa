package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
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

class MarkNotificationAsReadUseCaseTest : BaseTest() {
    private lateinit var markNotificationAsReadUseCase: MarkNotificationAsReadUseCase
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        markNotificationAsReadUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success when repository marks as read`() =
        runTest {
            val notificationId = "1"
            everySuspend { notificationRepository.markAsRead(notificationId) } returns Result.success(Unit)

            val result = markNotificationAsReadUseCase(notificationId)

            assertTrue(result.isSuccess)
            verifySuspend { notificationRepository.markAsRead(notificationId) }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val notificationId = "1"
            val exception = Exception("Failed to mark as read")
            everySuspend { notificationRepository.markAsRead(notificationId) } returns Result.failure(exception)

            val result = markNotificationAsReadUseCase(notificationId)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
            verifySuspend { notificationRepository.markAsRead(notificationId) }
        }
}
