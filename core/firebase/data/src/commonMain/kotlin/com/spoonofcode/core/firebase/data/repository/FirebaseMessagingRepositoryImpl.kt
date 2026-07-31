package com.spoonofcode.core.firebase.data.repository

import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository

expect class FirebaseMessagingRepositoryImpl(
    poaLogger: POALogger,
) : FirebaseMessagingRepository {
    override suspend fun getMessageToken(): Result<String>
    override suspend fun subscribeToTopic(topic: String): Result<Unit>
    override suspend fun unsubscribeFromTopic(topic: String): Result<Unit>
}
