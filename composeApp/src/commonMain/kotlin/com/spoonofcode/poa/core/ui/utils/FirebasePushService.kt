package com.spoonofcode.poa.core.ui.utils

expect class FirebasePushService {
    suspend fun getMessageToken(): String
    suspend fun subscribeToTopic(topic: String)
    suspend fun unsubscribeFromTopic(topic: String)
}