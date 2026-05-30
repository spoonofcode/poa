package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class SendNotificationUseCase(
    private val notificationRepository: NotificationRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topics: List<String>,
        deliveryDateTime: LocalDateTime,
        expirationDateTime: LocalDateTime,
    ): Result<Unit> = notificationRepository.create(
        title = title,
        text = text,
        link = link,
        topics = topics,
        deliveryDateTime = deliveryDateTime,
        expirationDateTime = expirationDateTime,
        ownerId = sessionRepository.getSessionUserId(),
    )
}