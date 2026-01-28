package com.spoonofcode.core.data.domain.usecase

import com.spoonofcode.core.data.network.SessionManager

// TODO #109 Fix after migration to feature modules
class SubscribeUserTopicsUseCase(
//    private val productRepository: ProductRepository,
//    private val sessionManager: SessionManager,
//    private val firebasePushService: FirebasePushService,
) {
    suspend operator fun invoke() {
//        subscribeToGlobalTopic()
//        subscribeByUserId()
//        subscribeBySeriesId()
    }

//    private suspend fun subscribeByUserId() {
//        val userId = sessionManager.getSessionUserId()
//        firebasePushService.subscribeToTopic("${TOPIC_USER_ID}${userId}")
//    }
//
//    private suspend fun subscribeBySeriesId() {
//        productRepository.getProductSeriesIds().onSuccess {
//            it.forEach { seriesId ->
//                firebasePushService.subscribeToTopic("${TOPIC_PRODUCT_SERIE_ID}${seriesId}")
//            }
//        }.onFailure {
//            // TODO: Add logger
//            println(it)
//        }
//    }
//
//    private suspend fun subscribeToGlobalTopic() {
//        firebasePushService.subscribeToTopic(TOPIC_GLOBAL)
//    }

    companion object {
        private const val TOPIC_GLOBAL = "topic_global"
        private const val TOPIC_USER_ID = "topic_user_id_"
        private const val TOPIC_PRODUCT_SERIE_ID = "topic_product_serie_id_"
    }
}