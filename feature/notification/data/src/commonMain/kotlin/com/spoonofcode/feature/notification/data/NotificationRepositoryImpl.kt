package com.spoonofcode.feature.notification.data

import com.spoonofcode.core.data.ext.toZonedInstant
import com.spoonofcode.feature.notification.data.mappers.toNotification
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class NotificationRepositoryImpl(
    private val remoteNotificationDataSource: RemoteNotificationDataSource,
) : NotificationRepository {
    override suspend fun create(
        title: String,
        text: String,
        link: String?,
        topic: String,
        expirationDateTime: LocalDateTime?
    ): Result<Unit> = remoteNotificationDataSource.create(
        title = title,
        text = text,
        link = link,
        topic = topic,
        expirationDateTime = expirationDateTime?.toZonedInstant(),
    ).map { Unit }

    override suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String?,
        topic: String,
        expirationDateTime: LocalDateTime?
    ): Result<Unit> = remoteNotificationDataSource.update(
        notificationId = notificationId,
        title = title,
        text = text,
        link = link,
        topic = topic,
        expirationDateTime = expirationDateTime?.toZonedInstant(),
    ).map { Unit }

    override suspend fun delete(notificationId: String): Result<Unit> =
        remoteNotificationDataSource.delete(
            notificationId = notificationId
        )

    override suspend fun read(notificationId: String): Result<Notification> =
        remoteNotificationDataSource.read(notificationId).map { it.toNotification() }

    override suspend fun readAll(): Result<List<Notification>> =
        remoteNotificationDataSource.readAll()
            .map { response ->
                response.map { it.toNotification() }
            }
}