package com.spoonofcode.core.firebase.data.repository

import com.spoonofcode.core.firebase.data.remote.RemoteFirebaseMessagingDataSource
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository

internal class FirebaseMessagingRepositoryImpl(
    private val remoteFirebaseMessagingDataSource: RemoteFirebaseMessagingDataSource,
) : FirebaseMessagingRepository {
    override suspend fun getMessageToken(): Result<String> = remoteFirebaseMessagingDataSource.getMessageToken()

    override suspend fun subscribeToTopic(topic: String): Result<Unit> = remoteFirebaseMessagingDataSource.subscribeToTopic(topic)

    override suspend fun unsubscribeFromTopic(topic: String): Result<Unit> = remoteFirebaseMessagingDataSource.unsubscribeFromTopic(topic)
}
