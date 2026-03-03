package com.spoonofcode.core.firebase.usecase

import com.spoonofcode.core.firebase.FirebaseMessagingService

class SubscribeToFirebaseMessagingTopicUseCase(
    private val firebaseMessagingService: FirebaseMessagingService,
) {
    suspend operator fun invoke(topic: String) {
        firebaseMessagingService.subscribeToTopic(topic)
    }
}