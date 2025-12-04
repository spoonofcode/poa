package com.spoonofcode.poa.core.domain.notification

import com.spoonofcode.poa.core.data.repository.NotificationRepository
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
    ) {
        notificationRepository.create(
            title = title,
            text = text,
            link = link,
            imageUrl = imageUrl,
            topics = topics,
            expirationDateTime = expirationDateTime,
            alreadyRead = alreadyRead,
        )
    }
}