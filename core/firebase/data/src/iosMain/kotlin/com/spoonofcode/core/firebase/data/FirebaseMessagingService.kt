package com.spoonofcode.core.firebase.data

actual class FirebaseMessagingService {
//    actual suspend fun getMessageToken(): String? = suspendCancellableCoroutine { cont ->
//        FirebasePushServiceObjC.getToken { token ->
//            cont.resume(token)
//        }
//    }
//
//    actual fun subscribeToTopic(topic: String) {
//        FirebasePushServiceObjC.subscribeWithTopic(topic)
//    }
//
//    actual fun unsubscribeFromTopic(topic: String) {
//        FirebasePushServiceObjC.unsubscribeWithTopic(topic)
//    }
    actual suspend fun getMessageToken(): String {
    TODO("Not yet implemented")
    }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return Result.success(Unit)
    }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return Result.success(Unit)

    }
}