package com.spoonofcode.feature.notification.data

import com.spoonofcode.feature.notification.data.mappers.toNotification
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class NotificationRepositoryImpl(
    private val remoteNotificationDataSource: RemoteNotificationDataSource,
) : NotificationRepository {
    override suspend fun send(
        title: String,
        text: String,
        link: String?,
        imageUrl: String?,
        topics: List<String>,
        expirationDateTime: LocalDateTime?,
        alreadyRead: Boolean
    ): Result<Unit> = remoteNotificationDataSource.create(
        title = title,
        text = text,
        link = link,
        imageUrl = imageUrl,
        topics = topics,
        expirationDateTime = expirationDateTime,
        alreadyRead = alreadyRead,
    )

    override suspend fun read(notificationId: String): Result<Notification> =
        remoteNotificationDataSource.read(notificationId).map { it.toNotification() }

    override suspend fun readAll(): Result<List<Notification>> =
        remoteNotificationDataSource.readAll()
            .map { response ->
                response.map { it.toNotification() }
            }
}