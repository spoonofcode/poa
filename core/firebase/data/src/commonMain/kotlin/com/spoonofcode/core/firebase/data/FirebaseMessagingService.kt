package com.spoonofcode.core.firebase.data

expect class FirebaseMessagingService {
    suspend fun getMessageToken(): String
    suspend fun subscribeToTopic(topic: String): Result<Unit>
    suspend fun unsubscribeFromTopic(topic: String): Result<Unit>
}