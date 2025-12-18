package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
import kotlinx.datetime.LocalDateTime

class SendNotificationUseCase(
    private val notificationDataSource: NotificationDataSource,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
        link: String? = null,
        imageUrl: String? = null,
        topics: List<String>,
        expirationDateTime: LocalDateTime? = null,
        alreadyRead: Boolean = false,
    ): Result<Unit> {
        return notificationDataSource.create(
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