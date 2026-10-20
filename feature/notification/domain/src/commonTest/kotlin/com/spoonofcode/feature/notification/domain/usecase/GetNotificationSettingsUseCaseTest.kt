package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.domain.di.notificationDomainTestModule
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
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

class GetNotificationSettingsUseCaseTest : BaseTest() {

    private lateinit var getNotificationSettingsUseCase: GetNotificationSettingsUseCase
    private lateinit var notificationRepository: NotificationRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        getNotificationSettingsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke returns notification settings from repository`() = runTest {
        val expectedSettings = NotificationSettings(isNotificationsPersonalizedEnabled = true)
        everySuspend { notificationRepository.getNotificationSettings() } returns Result.success(expectedSettings)

        val result = getNotificationSettingsUseCase()

        assertTrue(result.isSuccess)
        assertEquals(expectedSettings, result.getOrNull())
        verifySuspend { notificationRepository.getNotificationSettings() }
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val exception = Exception("Failed to get settings")
        everySuspend { notificationRepository.getNotificationSettings() } returns Result.failure(exception)

        val result = getNotificationSettingsUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verifySuspend { notificationRepository.getNotificationSettings() }
    }
}
