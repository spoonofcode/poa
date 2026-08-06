package com.spoonofcode.core.firebase.data.remote

import com.spoonofcode.core.logging.POALogger

expect class RemoteFirebaseMessagingDataSource(
    poaLogger: POALogger
) {
    suspend fun getMessageToken(): Result<String>
    suspend fun subscribeToTopic(topic: String): Result<Unit>
    suspend fun unsubscribeFromTopic(topic: String): Result<Unit>
}
