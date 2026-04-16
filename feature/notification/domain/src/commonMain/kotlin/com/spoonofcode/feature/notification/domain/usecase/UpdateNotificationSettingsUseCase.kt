package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.firebase.data.FirebaseMessagingService
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class UpdateNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
    private val firebaseMessagingService: FirebaseMessagingService,
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
            firebaseMessagingService.subscribeToTopic(topic)
        } else {
            firebaseMessagingService.unsubscribeFromTopic(topic)
        }
    }

    private fun saveLocalSettings(settings: NotificationSettings) {
        sessionManager.saveNotificationsPersonalizedSettings(settings.isNotificationsPersonalizedEnabled)
        sessionManager.saveNotificationsFromPartnersSettings(settings.isNotificationsFromPartnersEnabled)
        sessionManager.saveNotificationsMyProductSeriesSettings(settings.isNotificationsMyProductSeriesEnabled)
    }

    private fun getUserTopic(): String =
        NotificationType.user.createTopic(sessionManager.getSessionUserId())

    private fun getPartnerTopic(partnerId: String): String =
        NotificationType.partner.createTopic(partnerId)

    private fun getSerieTopic(seriesId: String): String =
        NotificationType.serie.createTopic(seriesId)

}