package com.spoonofcode.feature.partner.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import kotlinx.datetime.LocalDateTime

class PartnerSendNotificationUseCase(
    private val notificationRepository: PartnerNotificationRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        link: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
    ): Result<Unit> = notificationRepository.createNotification(
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime,
        expirationDateTime = expirationDateTime,
        ownerId = sessionRepository.getSessionUserId(),
    )
}
