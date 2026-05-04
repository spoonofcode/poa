package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class DeleteProductFromUserUseCase(
    private val productRepository: ProductRepository,
    private val firebaseMessagingRepository: FirebaseMessagingRepository,
) {
    suspend operator fun invoke(
        productId: String,
        productSerieId: String? = null,
    ): Result<Unit> {
        val deleteProductResult = productRepository.deleteProductFromUser(productId = productId)

        if (deleteProductResult.isFailure) {
            return deleteProductResult
        }
        return if (productSerieId != null)
            firebaseMessagingRepository.unsubscribeFromTopic(
                topic = getSerieTopic(seriesId = productSerieId)
            ) else {
            Result.success(Unit)
        }
    }

    private fun getSerieTopic(seriesId: String): String =
        NotificationType.serie.createTopic(seriesId)
}