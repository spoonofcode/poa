package com.spoonofcode.core.firebase.data.repository

import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository

expect class FirebaseMessagingRepositoryImpl : FirebaseMessagingRepository {
    override suspend fun getMessageToken(): String
    override suspend fun subscribeToTopic(topic: String): Result<Unit>
    override suspend fun unsubscribeFromTopic(topic: String): Result<Unit>
}