package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.core.data.logging.KermitLogger

class UpdateNotificationSettingsUseCase(
    private val sessionRepository: SessionRepository,
    private val notificationRepository: NotificationRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
    private val topicSyncHandlers: List<NotificationTopicSyncHandler>,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings): Result<Unit> {
        handleTopicSubscription(
            isEnabled = notificationSettings.isNotificationsPersonalizedEnabled,
            topic = getUserTopic()
        ).getOrThrow()

        topicSyncHandlers.forEach { handler ->
            handler.syncTopics(notificationSettings).getOrThrow()
        }

        saveLocalSettings(notificationSettings)
        return Result.success(Unit)
    }

    private suspend fun handleTopicSubscription(isEnabled: Boolean, topic: String): Result<Unit> {
        return if (isEnabled) {
            poaLogger.debug("Subscribing to topic: $topic")
            firebaseMessagingRepository.subscribeToTopic(topic)
        } else {
            poaLogger.debug("Unsubscribing from topic: $topic")
            firebaseMessagingRepository.unsubscribeFromTopic(topic)
        }
    }

    private suspend fun saveLocalSettings(settings: NotificationSettings) {
        notificationRepository.setNotificationSettings(
            notificationsPersonalizedEnabled = settings.isNotificationsPersonalizedEnabled,
            notificationsFromPartnersEnabled = settings.isNotificationsFromPartnersEnabled,
            notificationsMyProductSeriesEnabled = settings.isNotificationsMyProductSeriesEnabled,
        )
    }

    private suspend fun getUserTopic(): String =
        NotificationType.user.createTopic(sessionRepository.getSessionUserId())

    companion object {
        private val poaLogger = KermitLogger
    }
}
