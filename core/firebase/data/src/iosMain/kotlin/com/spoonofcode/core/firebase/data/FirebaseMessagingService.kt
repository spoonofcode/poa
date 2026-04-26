package com.spoonofcode.core.firebase.data

import cocoapods.FirebaseMessaging.FIRMessaging
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalForeignApi::class)
actual class FirebaseMessagingService {

    actual suspend fun getMessageToken(): String {
        TODO("Not yet implemented")
    }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return Result.success(Unit)
    }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return Result.success(Unit)

    }

    // TODO Uncomment this code (proper implementation but we need apple developer account)
//
//    actual suspend fun getMessageToken(): String = suspendCancellableCoroutine { continuation ->
//        FIRMessaging.messaging().tokenWithCompletion { token, error ->
//            if (error != null) {
//                continuation.resumeWithException(Exception(error.localizedDescription))
//            } else if (token != null) {
//                continuation.resume(token)
//            } else {
//                continuation.resumeWithException(Exception("Token is null"))
//            }
//        }
//    }
//
//    actual suspend fun subscribeToTopic(topic: String): Result<Unit> = suspendCancellableCoroutine { continuation ->
//        FIRMessaging.messaging().subscribeToTopic(topic) { error ->
//            if (error != null) {
//                val e = Exception("topic=$topic subscription error: ${error.localizedDescription}")
//                continuation.resume(Result.failure(e))
//            } else {
//                continuation.resume(Result.success(Unit))
//            }
//        }
//    }
//
//    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> = suspendCancellableCoroutine { continuation ->
//        FIRMessaging.messaging().unsubscribeFromTopic(topic) { error ->
//            if (error != null) {
//                val e = Exception("topic=$topic unsubscription error: ${error.localizedDescription}")
//                continuation.resume(Result.failure(e))
//            } else {
//                continuation.resume(Result.success(Unit))
//            }
//        }
//    }
}