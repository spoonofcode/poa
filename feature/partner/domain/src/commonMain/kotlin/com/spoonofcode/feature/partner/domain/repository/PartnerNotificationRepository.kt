package com.spoonofcode.feature.partner.domain.repository

import com.spoonofcode.feature.partner.domain.model.PartnerNotification

interface PartnerNotificationRepository {
    suspend fun getNotifications(): Result<List<PartnerNotification>>
    suspend fun getNotification(notificationId: String): Result<PartnerNotification>
    suspend fun deleteNotification(notificationId: String): Result<Unit>
}
