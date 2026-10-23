package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.logging.POALogger
import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository

class UpdateNotificationSettingsUseCase(
    private val sessionRepository: SessionRepository,
    private val notificationRepository: NotificationRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
    private val topicSyncHandlers: List<NotificationTopicSyncHandler>,
    private val poaLogger: POALogger,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings): Result<Unit> =
        runCatching {
            handleTopicSubscription(
                isEnabled = notificationSettings.isNotificationsPersonalizedEnabled,
                topic = getUserTopic(),
            ).getOrThrow()

            topicSyncHandlers.forEach { handler ->
                handler.syncTopics(notificationSettings).getOrThrow()
            }

            notificationRepository
                .setNotificationSettings(
                    notificationsPersonalizedEnabled = notificationSettings.isNotificationsPersonalizedEnabled,
                    notificationsFromPartnersEnabled = notificationSettings.isNotificationsFromPartnersEnabled,
                    notificationsMyProductSeriesEnabled = notificationSettings.isNotificationsMyProductSeriesEnabled,
                ).getOrThrow()
        }

    private suspend fun handleTopicSubscription(
        isEnabled: Boolean,
        topic: String,
    ): Result<Unit> =
        if (isEnabled) {
            poaLogger.debug("Subscribing to topic: $topic")
            firebaseMessagingRepository.subscribeToTopic(topic)
        } else {
            poaLogger.debug("Unsubscribing from topic: $topic")
            firebaseMessagingRepository.unsubscribeFromTopic(topic)
        }

    private suspend fun getUserTopic(): String = NotificationType.USER.createTopic(sessionRepository.getSessionUserId())
}
