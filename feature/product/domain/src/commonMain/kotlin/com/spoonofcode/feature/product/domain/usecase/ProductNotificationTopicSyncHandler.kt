package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.usecase.NotificationTopicSyncHandler
import com.spoonofcode.feature.product.domain.repository.ProductRepository
import com.spoonofcode.core.data.logging.KermitLogger

class ProductNotificationTopicSyncHandler(
    private val productRepository: ProductRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
) : NotificationTopicSyncHandler {

    override suspend fun syncTopics(settings: NotificationSettings): Result<Unit> {
        return try {
            val products = productRepository.getUserProducts().getOrThrow()

            val partnerIds = products.mapNotNull { it.partnerId }.distinct()
            partnerIds.forEach { id ->
                handleTopicSubscription(
                    isEnabled = settings.isNotificationsFromPartnersEnabled,
                    topic = getPartnerTopic(id)
                ).getOrThrow()
            }

            val seriesIds = products.mapNotNull { it.seriesId }.distinct()
            seriesIds.forEach { id ->
                handleTopicSubscription(
                    isEnabled = settings.isNotificationsMyProductSeriesEnabled,
                    topic = getSerieTopic(id)
                ).getOrThrow()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
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

    private fun getPartnerTopic(partnerId: String): String =
        NotificationType.partner.createTopic(partnerId)

    private fun getSerieTopic(seriesId: String): String =
        NotificationType.serie.createTopic(seriesId)

    companion object {
        private val poaLogger = KermitLogger
    }
}
