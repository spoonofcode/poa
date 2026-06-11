package com.spoonofcode.feature.partner.data.repository

import com.spoonofcode.core.data.ext.toZonedInstant
import com.spoonofcode.feature.partner.data.mappers.toPartnerNotification
import com.spoonofcode.feature.partner.data.remote.RemotePartnerNotificationDataSource
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import kotlinx.datetime.LocalDateTime

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

    override suspend fun createNotification(
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String
    ): Result<Unit> = remoteDataSource.create(
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime.toZonedInstant(),
        expirationDateTime = expirationDateTime.toZonedInstant(),
        ownerId = ownerId,
    )

    override suspend fun updateNotification(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String
    ): Result<Unit> = remoteDataSource.update(
        notificationId = notificationId,
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime.toZonedInstant(),
        expirationDateTime = expirationDateTime.toZonedInstant(),
        ownerId = ownerId,
    )
}
