package com.spoonofcode.core.firebase.domain.usecase

import com.spoonofcode.core.firebase.data.FirebaseMessagingService

class UnsubscribeFromFirebaseMessagingTopicUseCase(
    private val firebaseMessagingService: FirebaseMessagingService,
) {
    suspend operator fun invoke(topic: String) {
        firebaseMessagingService.unsubscribeFromTopic(topic)
    }
}