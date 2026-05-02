package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.core.session.domain.SessionRepository
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class UpdateNotificationSettingsUseCase(
    private val sessionRepository: SessionRepository,
    private val notificationRepository: NotificationRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings): Result<Unit> {
        handleTopicSubscription(
            isEnabled = notificationSettings.isNotificationsPersonalizedEnabled,
            topic = getUserTopic()
        ).getOrThrow()

        val products = productRepository.getUserProducts().getOrThrow()

        val partnerIds = products.mapNotNull { it.partnerId }.distinct()
        partnerIds.forEach { id ->
            handleTopicSubscription(
                isEnabled = notificationSettings.isNotificationsFromPartnersEnabled,
                topic = getPartnerTopic(id)
            ).getOrThrow()
        }

        val seriesIds = products.mapNotNull { it.seriesId }.distinct()
        seriesIds.forEach { id ->
            handleTopicSubscription(
                isEnabled = notificationSettings.isNotificationsMyProductSeriesEnabled,
                topic = getSerieTopic(id)
            ).getOrThrow()
        }

        saveLocalSettings(notificationSettings)
        return Result.success(Unit)
    }

    private suspend fun handleTopicSubscription(isEnabled: Boolean, topic: String): Result<Unit> {
        return if (isEnabled) {
            firebaseMessagingRepository.subscribeToTopic(topic)
        } else {
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

    private fun getPartnerTopic(partnerId: String): String =
        NotificationType.partner.createTopic(partnerId)

    private fun getSerieTopic(seriesId: String): String =
        NotificationType.serie.createTopic(seriesId)

}