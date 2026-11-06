package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class AddProductToUserUseCase(
    private val productRepository: ProductRepository,
    private val notificationRepository: NotificationRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
) {
    suspend operator fun invoke(
        productId: String,
        productSerieId: String? = null,
    ): Result<Unit> {
        val addProductResult = productRepository.addProductToUser(productId = productId)

        if (addProductResult.isFailure) {
            return addProductResult
        }

        return if (productSerieId != null &&
            (notificationRepository.getNotificationSettings().getOrNull()?.isNotificationsMyProductSeriesEnabled == true)
        ) {
            firebaseMessagingRepository.subscribeToTopic(
                topic = getSerieTopic(seriesId = productSerieId),
            )
        } else {
            Result.success(Unit)
        }
    }

    private fun getSerieTopic(seriesId: String): String = NotificationType.SERIE.createTopic(seriesId)
}
