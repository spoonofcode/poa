package com.spoonofcode.core.firebase.data.repository

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import kotlinx.coroutines.tasks.await

actual class FirebaseMessagingRepositoryImpl : FirebaseMessagingRepository {
    actual override suspend fun getMessageToken(): Result<String> {
        return try {
            Result.success(Firebase.messaging.token.await())
        } catch (e: Exception) {
            Result.failure(Exception("Failed to get FCM token", e))
        }
    }

    actual override suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return try {
            Firebase.messaging.subscribeToTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("topic=$topic subscription error", e))
        }
    }

    actual override suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return try {
            Firebase.messaging.unsubscribeFromTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("topic=$topic unsubscription error", e))
        }
    }
}