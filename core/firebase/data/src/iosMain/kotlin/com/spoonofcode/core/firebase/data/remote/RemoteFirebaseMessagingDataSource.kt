package com.spoonofcode.core.firebase.data.remote

import cocoapods.FirebaseMessaging.FIRMessaging
import com.spoonofcode.core.logging.POALogger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
actual class RemoteFirebaseMessagingDataSource actual constructor(
    private val poaLogger: POALogger,
) {
    actual suspend fun getMessageToken(): Result<String> =
        suspendCancellableCoroutine { continuation ->
            poaLogger.debug("RemoteFirebaseMessagingDataSource: Fetching FCM token for iOS")
            FIRMessaging.messaging().tokenWithCompletion { token, error ->
                if (continuation.isActive) {
                    if (error != null) {
                        val exception = Exception(error.localizedDescription)
                        poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to get FCM token on iOS", exception)
                        continuation.resume(Result.failure(exception))
                    } else if (token != null) {
                        poaLogger.info("RemoteFirebaseMessagingDataSource: Successfully fetched FCM token")
                        continuation.resume(Result.success(token))
                    } else {
                        val exception = Exception("Failed to get FCM token: unknown error")
                        poaLogger.error("RemoteFirebaseMessagingDataSource: Unknown error getting FCM token on iOS", exception)
                        continuation.resume(Result.failure(exception))
                    }
                }
            }
        }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            poaLogger.info("RemoteFirebaseMessagingDataSource: Subscribing to topic: $topic on iOS")
            FIRMessaging.messaging().subscribeToTopic(topic) { error ->
                if (continuation.isActive) {
                    if (error != null) {
                        val exception = Exception(error.localizedDescription)
                        poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to subscribe to topic $topic on iOS", exception)
                        continuation.resume(Result.failure(exception))
                    } else {
                        poaLogger.info("RemoteFirebaseMessagingDataSource: Successfully subscribed to topic: $topic")
                        continuation.resume(Result.success(Unit))
                    }
                }
            }
        }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> =
        suspendCancellableCoroutine { continuation ->
            poaLogger.info("RemoteFirebaseMessagingDataSource: Unsubscribing from topic: $topic on iOS")
            FIRMessaging.messaging().unsubscribeFromTopic(topic) { error ->
                if (continuation.isActive) {
                    if (error != null) {
                        val exception = Exception(error.localizedDescription)
                        poaLogger.error("RemoteFirebaseMessagingDataSource: Failed to unsubscribe from topic $topic on iOS", exception)
                        continuation.resume(Result.failure(exception))
                    } else {
                        poaLogger.info("RemoteFirebaseMessagingDataSource: Successfully unsubscribed from topic: $topic")
                        continuation.resume(Result.success(Unit))
                    }
                }
            }
        }
}
