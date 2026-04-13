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
        if (notificationSettings.isNotificationsPersonalizedEnabled) {
            firebaseMessagingService.subscribeToTopic(getUserTopic())
                .onFailure { return Result.failure(it) }
        } else {
            firebaseMessagingService.unsubscribeFromTopic(getUserTopic())
                .onFailure { return Result.failure(it) }
        }

        if (notificationSettings.isNotificationsMyProductSeriesEnabled) {
            productRepository.getUserProducts().onSuccess { products ->
                val uniqueSeries = products.mapNotNull { it.seriesId }.distinct()
                uniqueSeries.forEach { seriesId ->
                    firebaseMessagingService.subscribeToTopic(getSerieTopic(seriesId))
                        .onFailure { return Result.failure(it) }
                }
            }
        } else {
            productRepository.getUserProducts().onSuccess { products ->
                val uniqueSeries = products.mapNotNull { it.seriesName }.distinct()
                uniqueSeries.forEach { seriesName ->
                    firebaseMessagingService.unsubscribeFromTopic(getSerieTopic(seriesName))
                        .onFailure { return Result.failure(it) }
                }
            }
        }

        sessionManager.saveSettingsPersonalizedPartnerNotifications(notificationSettings.isNotificationsPersonalizedEnabled)
        sessionManager.saveSettingsProductSeriesNotifications(notificationSettings.isNotificationsMyProductSeriesEnabled)

        return Result.success(Unit)
    }

    private fun getUserTopic(): String =
        NotificationType.user.createTopic(sessionManager.getSessionUserId())

    private fun getSerieTopic(seriesId: String): String =
        NotificationType.serie.createTopic(seriesId)

}