package com.spoonofcode.poa.feature.notification.domain

import com.spoonofcode.poa.feature.notification.domain.model.Notification
import kotlinx.datetime.LocalDateTime

interface NotificationDataSource {
    suspend fun create(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topics: List<String>,
        expirationDateTime: LocalDateTime? = null,
        alreadyRead: Boolean = false,
    ): Result<Unit>

    suspend fun read(notificationId: String): Result<Notification>
    suspend fun readAll(): Result<List<Notification>>
}