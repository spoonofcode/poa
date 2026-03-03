package com.spoonofcode.core.firebase.data

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await

actual class FirebaseMessagingService() {
    actual suspend fun getMessageToken(): String {
        return Firebase.messaging.token.await()
    }

    actual suspend fun subscribeToTopic(topic: String) {
        Firebase.messaging.subscribeToTopic(topic).await()
    }

    actual suspend fun unsubscribeFromTopic(topic: String) {
        Firebase.messaging.unsubscribeFromTopic(topic).await()
    }
}