package com.spoonofcode.feature.partner.domain.repository

import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import kotlinx.datetime.LocalDateTime

interface PartnerNotificationRepository {
    suspend fun getNotifications(): Result<List<PartnerNotification>>

    suspend fun getNotification(notificationId: String): Result<PartnerNotification>

    suspend fun deleteNotification(notificationId: String): Result<Unit>

    suspend fun createNotification(
        title: String,
        text: String,
        link: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit>

    suspend fun updateNotification(
        notificationId: String,
        title: String,
        text: String,
        link: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
        ownerId: String,
    ): Result<Unit>
}
