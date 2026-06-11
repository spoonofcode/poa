package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.PartnerNotification

class DeletePartnerNotificationUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(notificationId: String): Result<List<PartnerNotification>> =
        notificationRepository.delete(notificationId)
}