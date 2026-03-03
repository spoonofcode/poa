package com.spoonofcode.core.firebase.usecase

import com.spoonofcode.core.firebase.FirebaseMessagingService

class UnsubscribeFromFirebaseMessagingTopicUseCase(
    private val firebaseMessagingService: FirebaseMessagingService,
) {
    suspend operator fun invoke(topic: String) {
        firebaseMessagingService.unsubscribeFromTopic(topic)
    }
}