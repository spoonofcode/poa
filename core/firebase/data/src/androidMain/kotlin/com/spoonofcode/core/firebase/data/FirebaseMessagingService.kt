package com.spoonofcode.core.firebase.data

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await

actual class FirebaseMessagingService() {
    actual suspend fun getMessageToken(): String {
        return Firebase.messaging.token.await()
    }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return try {
            Firebase.messaging.subscribeToTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("topic=$topic subscription error", e))
        }
    }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return try {
            Firebase.messaging.unsubscribeFromTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("topic=$topic  unsubscription error", e))
        }
    }
}