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

class GetNotificationsUseCaseTest : BaseTest() {

    private lateinit var getNotificationsUseCase: GetNotificationsUseCase
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        getNotificationsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns success with sorted notifications`() = runTest {
        val result = getNotificationsUseCase()

        assertTrue(result.isSuccess)
        assertEquals(NotificationMockData.NOTIFICATIONS, result.getOrNull())
        verifySuspend { notificationRepository.readAll() }
    }

    @Test
    fun `invoke returns failure when repository throws`() = runTest {
        val exception = Exception("Error reading notifications")
        everySuspend { notificationRepository.readAll() } returns Result.failure(exception)

        val result = getNotificationsUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { notificationRepository.readAll() }
    }
}
