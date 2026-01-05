package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

class SendNotificationUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topics: List<String>,
        expirationDateTime: LocalDateTime? = null,
        alreadyRead: Boolean = false,
    ): Result<Unit> = notificationRepository.send(
        title = title,
        text = text,
        link = link,
        imageUrl = imageUrl,
        topics = topics,
        expirationDateTime = expirationDateTime,
        alreadyRead = alreadyRead,
    )
}