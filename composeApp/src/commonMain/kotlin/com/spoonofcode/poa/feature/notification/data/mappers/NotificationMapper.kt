package com.spoonofcode.poa.feature.notification.data.mappers

import com.spoonofcode.poa.feature.notification.data.remote.model.NotificationResponse
import com.spoonofcode.poa.feature.notification.domain.model.Notification

fun NotificationResponse.toNotification(): Notification {
    return Notification(
        id = id,
        title = title,
        text = text,
        link = link,
        expirationDateTime = expirationDateTime,
        alreadyRead = alreadyRead,
    )
}