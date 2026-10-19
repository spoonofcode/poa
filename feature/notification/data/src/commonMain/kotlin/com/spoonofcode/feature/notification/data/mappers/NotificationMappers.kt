package com.spoonofcode.feature.notification.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.notification.data.remote.model.NotificationResponse
import com.spoonofcode.feature.notification.domain.model.Notification

internal fun NotificationResponse.toNotification(): Notification = Notification(
    id = id,
    title = title,
    text = text,
    link = link,
    deliveryDateTime = deliveryDateTime.toZonedLocalDateTime(),
    createdDataTime = createdDateTime.toZonedLocalDateTime(),
    modificationDateTime = modificationDateTime.toZonedLocalDateTime(),
    expirationDateTime = expirationDateTime.toZonedLocalDateTime(),
    state = state,
    topics = topics,
    ownerId = ownerId,
)