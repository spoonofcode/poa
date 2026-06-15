package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository

class GetPartnerNotificationUseCase(
    private val notificationRepository: PartnerNotificationRepository,
) {
    suspend operator fun invoke(notificationId: String): Result<PartnerNotification> =
        notificationRepository.getNotification(notificationId)
}
