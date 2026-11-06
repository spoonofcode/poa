package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.core.test.base.BaseTest
import com.spoonofcode.feature.notification.domain.di.notificationDomainTestModule
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
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

class UpdateNotificationSettingsUseCaseTest : BaseTest() {
    private lateinit var updateNotificationSettingsUseCase: UpdateNotificationSettingsUseCase
    private lateinit var notificationRepository: NotificationRepository
    private lateinit var firebaseMessagingRepository: FirebaseMessagingRepository
    private lateinit var sessionRepository: SessionRepository

    @BeforeTest
    override fun beforeTest() {
        modules = arrayOf(notificationDomainTestModule)
        super.beforeTest()
        notificationRepository = getKoin().get()
        firebaseMessagingRepository = getKoin().get()
        sessionRepository = getKoin().get()
        updateNotificationSettingsUseCase = getSut()
    }

    @AfterTest
    override fun afterTest() {
        super.afterTest()
    }

    @Test
    fun `invoke subscribes to user topic and sets notification settings when personalized notifications enabled`() =
        runTest {
            val userId = "user123"
            val userTopic = NotificationType.USER.createTopic(userId)
            val settings =
                NotificationSettings(
                    isNotificationsPersonalizedEnabled = true,
                    isNotificationsFromPartnersEnabled = true,
                    isNotificationsMyProductSeriesEnabled = true,
                )

            everySuspend { sessionRepository.getSessionUserId() } returns userId
            everySuspend { firebaseMessagingRepository.subscribeToTopic(userTopic) } returns Result.success(Unit)
            everySuspend {
                notificationRepository.setNotificationSettings(
                    notificationsPersonalizedEnabled = true,
                    notificationsFromPartnersEnabled = true,
                    notificationsMyProductSeriesEnabled = true,
                )
            } returns Result.success(Unit)

            val result = updateNotificationSettingsUseCase(settings)

            assertTrue(result.isSuccess)
            verifySuspend { firebaseMessagingRepository.subscribeToTopic(userTopic) }
            verifySuspend {
                notificationRepository.setNotificationSettings(
                    notificationsPersonalizedEnabled = true,
                    notificationsFromPartnersEnabled = true,
                    notificationsMyProductSeriesEnabled = true,
                )
            }
        }

    @Test
    fun `invoke unsubscribes from user topic when personalized notifications disabled`() =
        runTest {
            val userId = "user123"
            val userTopic = NotificationType.USER.createTopic(userId)
            val settings =
                NotificationSettings(
                    isNotificationsPersonalizedEnabled = false,
                    isNotificationsFromPartnersEnabled = false,
                    isNotificationsMyProductSeriesEnabled = false,
                )

            everySuspend { sessionRepository.getSessionUserId() } returns userId
            everySuspend { firebaseMessagingRepository.unsubscribeFromTopic(userTopic) } returns Result.success(Unit)
            everySuspend {
                notificationRepository.setNotificationSettings(
                    notificationsPersonalizedEnabled = false,
                    notificationsFromPartnersEnabled = false,
                    notificationsMyProductSeriesEnabled = false,
                )
            } returns Result.success(Unit)

            val result = updateNotificationSettingsUseCase(settings)

            assertTrue(result.isSuccess)
            verifySuspend { firebaseMessagingRepository.unsubscribeFromTopic(userTopic) }
            verifySuspend {
                notificationRepository.setNotificationSettings(
                    notificationsPersonalizedEnabled = false,
                    notificationsFromPartnersEnabled = false,
                    notificationsMyProductSeriesEnabled = false,
                )
            }
        }

    @Test
    fun `invoke returns failure when repository fails`() =
        runTest {
            val userId = "user123"
            val userTopic = NotificationType.USER.createTopic(userId)
            val settings =
                NotificationSettings(
                    isNotificationsPersonalizedEnabled = true,
                    isNotificationsFromPartnersEnabled = true,
                    isNotificationsMyProductSeriesEnabled = true,
                )
            val exception = Exception("Failed to set settings")

            everySuspend { sessionRepository.getSessionUserId() } returns userId
            everySuspend { firebaseMessagingRepository.subscribeToTopic(userTopic) } returns Result.success(Unit)
            everySuspend {
                notificationRepository.setNotificationSettings(
                    notificationsPersonalizedEnabled = true,
                    notificationsFromPartnersEnabled = true,
                    notificationsMyProductSeriesEnabled = true,
                )
            } returns Result.failure(exception)

            val result = updateNotificationSettingsUseCase(settings)

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
        }
}
