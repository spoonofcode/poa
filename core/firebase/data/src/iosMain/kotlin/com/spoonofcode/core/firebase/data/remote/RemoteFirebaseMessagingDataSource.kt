package com.spoonofcode.core.firebase.data.remote

import com.spoonofcode.core.logging.POALogger

actual class RemoteFirebaseMessagingDataSource actual constructor(
    private val poaLogger: POALogger,
) {
    actual suspend fun getMessageToken(): Result<String> {
        return Result.failure(Exception("Not yet implemented for iOS"))
    }

    actual suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return Result.failure(Exception("Not yet implemented for iOS"))
    }

    actual suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return Result.failure(Exception("Not yet implemented for iOS"))
    }
}
