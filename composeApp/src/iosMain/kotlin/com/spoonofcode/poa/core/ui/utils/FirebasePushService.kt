package com.spoonofcode.poa.core.ui.utils

actual class FirebasePushService {
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

    actual suspend fun subscribeToTopic(topic: String) {
        TODO("Not yet implemented")
    }

    actual suspend fun unsubscribeFromTopic(topic: String) {
        TODO("Not yet implemented")
    }
}