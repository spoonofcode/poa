package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class SendNotificationUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topic: String,
        expirationDateTime: LocalDateTime? = null,
    ): Result<Unit> = notificationRepository.create(
        title = title,
        text = text,
        link = link,
        topic = topic,
        expirationDateTime = expirationDateTime,
    )
}