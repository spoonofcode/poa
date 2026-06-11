package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository

class DeletePartnerNotificationUseCase(
    private val notificationRepository: PartnerNotificationRepository,
) {
    suspend operator fun invoke(notificationId: String): Result<List<PartnerNotification>> =
        notificationRepository.deleteNotification(notificationId).fold(
            onSuccess = { notificationRepository.getNotifications() },
            onFailure = { Result.failure(it) }
        )
}
