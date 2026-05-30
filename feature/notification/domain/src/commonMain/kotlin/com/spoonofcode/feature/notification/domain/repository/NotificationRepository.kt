package com.spoonofcode.feature.notification.domain.repository

import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.model.Notification
import kotlinx.datetime.LocalDateTime

interface NotificationRepository {
    suspend fun create(
        title: String,
        text: String,
        link: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit>

    suspend fun update(
        notificationId: String,
        title: String,
        text: String,
        link: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit>

    suspend fun delete(notificationId: String): Result<Unit>

    suspend fun read(notificationId: String): Result<Notification>
    suspend fun readAll(): Result<List<Notification>>

    suspend fun markAsRead(notificationId: String)
    suspend fun isRead(notificationId: String): Boolean

    suspend fun getNotificationSettings(): NotificationSettings
    suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    )
}
