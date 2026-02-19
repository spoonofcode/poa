package com.spoonofcode.core.firebase

expect class FirebaseMessagingService {
    suspend fun getMessageToken(): String
    suspend fun subscribeToTopic(topic: String)
    suspend fun unsubscribeFromTopic(topic: String)
}