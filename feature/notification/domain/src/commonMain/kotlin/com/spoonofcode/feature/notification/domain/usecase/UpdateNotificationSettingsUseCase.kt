package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.firebase.data.FirebaseMessagingService
import com.spoonofcode.feature.notification.domain.FirebaseMessagingTopicPrefixes
import com.spoonofcode.feature.notification.domain.model.NotificationSettings

class UpdateNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
    private val firebaseMessagingService: FirebaseMessagingService,
//    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings): Result<Unit> {
        if (notificationSettings.isPersonalizedPartnerNotificationsEnabled) {
            firebaseMessagingService.subscribeToTopic(getUserTopic()).onFailure { return Result.failure(it) }
//            firebaseMessagingService.subscribeToTopic(getSerieTopic()).onFailure { return Result.failure(it) }
        } else {
            firebaseMessagingService.unsubscribeFromTopic(getUserTopic()).onFailure { return Result.failure(it) }
        }

//        if (notificationSettings.isProductSeriesNotificationsEnabled) {
//            productRepository.getUserProducts().onSuccess { products ->
//                products.forEach { product->
//                    firebaseMessagingService.subscribeToTopic(getSerieTopic(product.seriesName))
//                }
//            }
//                .onFailure {
//                    showErrorView()
//                }
//            firebaseMessagingService.subscribeToTopic(getSerieTopic())
//        } else {
//            firebaseMessagingService.unsubscribeFromTopic(getSerieTopic())
//        }

        sessionManager.saveSettingsPersonalizedPartnerNotifications(notificationSettings.isPersonalizedPartnerNotificationsEnabled)
        sessionManager.saveSettingsProductSeriesNotifications(notificationSettings.isProductSeriesNotificationsEnabled)

        return Result.success(Unit)
    }

    private fun getUserTopic(): String =
        FirebaseMessagingTopicPrefixes.user.createTopic(sessionManager.getSessionUserEmail())

    private fun getSerieTopic(serieName:String): String =
        FirebaseMessagingTopicPrefixes.serie.createTopic(sessionManager.getSessionUserEmail())

}