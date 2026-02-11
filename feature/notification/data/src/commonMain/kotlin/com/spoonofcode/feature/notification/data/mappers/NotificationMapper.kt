package com.spoonofcode.feature.notification.data.mappers

import com.spoonofcode.feature.notification.data.remote.model.NotificationResponse
import com.spoonofcode.feature.notification.domain.model.Notification

fun NotificationResponse.toNotification(): Notification {
    return Notification(
        id = id,
        title = title,
        text = text,
        link = link,
        createdDataTime = createdDataTime,
        modificationDateTime = modificationDateTime,
        expirationDateTime = expirationDateTime,
        state = state,
        topic = topic,
    )
}