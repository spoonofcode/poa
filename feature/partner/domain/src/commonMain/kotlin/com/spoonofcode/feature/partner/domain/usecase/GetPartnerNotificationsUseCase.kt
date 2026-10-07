package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository

class GetPartnerNotificationsUseCase(
    private val notificationRepository: PartnerNotificationRepository,
) {
    suspend operator fun invoke(): Result<List<PartnerNotification>> =
        notificationRepository.getNotifications()
            .map { partnerNotifications ->
                partnerNotifications.sortedByDescending { it.deliveryDateTime }
            }
}
