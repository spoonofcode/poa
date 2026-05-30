package com.spoonofcode.feature.notification.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.notification.data.remote.model.NotificationResponse
import com.spoonofcode.feature.notification.domain.model.Notification

fun NotificationResponse.toNotification(): Notification {
    return Notification(
        id = id,
        title = title,
        text = text,
        link = link,
        deliveryDateTime = deliveryDateTime.toZonedLocalDateTime(),
        createdDataTime = createdDataTime.toZonedLocalDateTime(),
        modificationDateTime = modificationDateTime.toZonedLocalDateTime(),
        expirationDateTime = expirationDateTime.toZonedLocalDateTime(),
        state = state,
        topics = topics,
        ownerId = ownerId,
    )
}