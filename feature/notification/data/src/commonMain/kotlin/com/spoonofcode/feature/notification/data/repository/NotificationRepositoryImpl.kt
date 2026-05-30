package com.spoonofcode.feature.notification.data.repository

import com.spoonofcode.core.data.ext.toZonedInstant
import com.spoonofcode.feature.notification.data.local.LocalNotificationDataSource
import com.spoonofcode.feature.notification.data.mappers.toNotification
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class NotificationRepositoryImpl(
    private val remoteNotificationDataSource: RemoteNotificationDataSource,
    private val localNotificationDataSource: LocalNotificationDataSource,
) : NotificationRepository {


    override suspend fun create(
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit> = remoteNotificationDataSource.create(
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime.toZonedInstant(),
        expirationDateTime = expirationDateTime.toZonedInstant(),
        ownerId = ownerId,
    ).map { Unit }

    override suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit> = remoteNotificationDataSource.update(
        notificationId = notificationId,
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime.toZonedInstant(),
        expirationDateTime = expirationDateTime.toZonedInstant(),
        ownerId = ownerId,
    ).map { Unit }

    override suspend fun delete(notificationId: String): Result<Unit> =
        remoteNotificationDataSource.delete(
            notificationId = notificationId
        )

    override suspend fun read(notificationId: String): Result<Notification> =
        remoteNotificationDataSource.read(notificationId).map {
            it.toNotification().copy(isRead = isRead(notificationId))
        }

    override suspend fun readAll(): Result<List<Notification>> =
        remoteNotificationDataSource.readAll()
            .map { response ->
                response.map {
                    it.toNotification().copy(isRead = isRead(it.id))
                }
            }

    override suspend fun markAsRead(notificationId: String) {
        localNotificationDataSource.markAsRead(notificationId = notificationId)
    }

    override suspend fun isRead(notificationId: String): Boolean =
        localNotificationDataSource.isRead(notificationId = notificationId)

    override suspend fun getNotificationSettings(): NotificationSettings {
        return localNotificationDataSource.getNotificationSettings()
    }

    override suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    ) {
        localNotificationDataSource.setNotificationSettings(
            notificationsPersonalizedEnabled = notificationsPersonalizedEnabled,
            notificationsFromPartnersEnabled = notificationsFromPartnersEnabled,
            notificationsMyProductSeriesEnabled = notificationsMyProductSeriesEnabled,

            )
    }
}