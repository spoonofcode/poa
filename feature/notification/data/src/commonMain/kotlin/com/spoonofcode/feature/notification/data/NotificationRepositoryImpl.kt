package com.spoonofcode.feature.notification.data

import com.russhwolf.settings.Settings
import com.spoonofcode.core.data.ext.toZonedInstant
import com.spoonofcode.feature.notification.data.mappers.toNotification
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class NotificationRepositoryImpl(
    private val remoteNotificationDataSource: RemoteNotificationDataSource,
    private val settings: Settings,
) : NotificationRepository {

    private companion object {
        const val READ_NOTIFICATIONS_KEY = "read_notifications_ids"
    }

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
        val currentRead = settings.getString(READ_NOTIFICATIONS_KEY, "")
        if (!currentRead.contains(notificationId)) {
            val updatedRead =
                if (currentRead.isEmpty()) notificationId else "$currentRead,$notificationId"
            settings.putString(READ_NOTIFICATIONS_KEY, updatedRead)
        }
    }

    override suspend fun isRead(notificationId: String): Boolean {
        return settings.getString(READ_NOTIFICATIONS_KEY, "").contains(notificationId)
    }
}
