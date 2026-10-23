package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository

class DeletePartnerNotificationUseCase(
    private val notificationRepository: PartnerNotificationRepository,
) {
    suspend operator fun invoke(notificationId: String): Result<Unit> = notificationRepository.deleteNotification(notificationId)
}
