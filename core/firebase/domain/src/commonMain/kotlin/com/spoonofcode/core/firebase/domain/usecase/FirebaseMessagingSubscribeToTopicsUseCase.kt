package com.spoonofcode.core.firebase.domain.usecase

import com.spoonofcode.core.firebase.data.FirebaseMessagingService

class FirebaseMessagingSubscribeToTopicsUseCase(
    private val firebaseMessagingService: FirebaseMessagingService,
) {
    suspend operator fun invoke(topics: List<String>) {
        topics.forEach { topic ->
            firebaseMessagingService.subscribeToTopic(topic)
        }
    }
}