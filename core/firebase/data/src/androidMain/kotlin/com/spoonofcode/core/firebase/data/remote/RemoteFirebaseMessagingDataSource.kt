package com.spoonofcode.core.firebase.data.remote

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.spoonofcode.core.logging.POALogger
import kotlinx.coroutines.tasks.await

actual class RemoteFirebaseMessagingDataSource actual constructor(
    private val poaLogger: POALogger,
) {
    actual suspend fun getMessageToken(): Result<String> {
        return try {
            poaLogger.debug("RemoteFirebaseMessagingDataSource: Fetching FCM token")
            Result.success(Firebase.messaging.token.await())
        } catch (e: Exception) {
            poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to get FCM token", e)
            Result.failure(e)
        }
    }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return try {
            poaLogger.info("RemoteFirebaseMessagingDataSource: Subscribing to topic: $topic")
            Firebase.messaging.subscribeToTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to subscribe to topic $topic", e)
            Result.failure(e)
        }
    }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return try {
            poaLogger.info("RemoteFirebaseMessagingDataSource: Unsubscribing from topic: $topic")
            Firebase.messaging.unsubscribeFromTopic(topic).await()
            Result.success(Unit)
        } catch (e: Exception) {
            poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to unsubscribe from topic $topic", e)
            Result.failure(e)
        }
    }
}
