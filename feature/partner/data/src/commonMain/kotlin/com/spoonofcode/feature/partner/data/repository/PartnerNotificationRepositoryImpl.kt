package com.spoonofcode.feature.partner.data.repository

import com.spoonofcode.feature.partner.data.mappers.toPartnerNotification
import com.spoonofcode.feature.partner.data.remote.RemotePartnerNotificationDataSource
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository

class PartnerNotificationRepositoryImpl(
    private val remoteDataSource: RemotePartnerNotificationDataSource,
) : PartnerNotificationRepository {

    override suspend fun getNotifications(): Result<List<PartnerNotification>> =
        remoteDataSource.readAll().map { response ->
            response.map { it.toPartnerNotification() }
        }

    override suspend fun getNotification(notificationId: String): Result<PartnerNotification> =
        remoteDataSource.read(notificationId).map { it.toPartnerNotification() }

    override suspend fun deleteNotification(notificationId: String): Result<Unit> =
        remoteDataSource.delete(notificationId)
}
