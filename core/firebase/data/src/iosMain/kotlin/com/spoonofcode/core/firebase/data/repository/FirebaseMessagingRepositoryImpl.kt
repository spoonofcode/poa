package com.spoonofcode.core.firebase.data.repository

import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class FirebaseMessagingRepositoryImpl actual constructor(
    private val poaLogger: POALogger,
) : FirebaseMessagingRepository {

    actual override suspend fun getMessageToken(): Result<String> {
        return Result.failure(Exception("Not yet implemented"))
    }

    actual override suspend fun subscribeToTopic(topic: String): Result<Unit> {
        return Result.failure(Exception("Not yet implemented"))
    }

    actual override suspend fun unsubscribeFromTopic(topic: String): Result<Unit> {
        return Result.failure(Exception("Not yet implemented"))
    }
}
