package com.spoonofcode.core.firebase.domain.repository

interface FirebaseMessagingRepository {
    suspend fun getMessageToken(): String
    suspend fun subscribeToTopic(topic: String): Result<Unit>
    suspend fun unsubscribeFromTopic(topic: String): Result<Unit>
}
