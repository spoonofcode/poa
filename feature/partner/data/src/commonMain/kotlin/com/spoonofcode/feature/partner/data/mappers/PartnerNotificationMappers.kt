package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.partner.data.model.PartnerNotificationResponse
import com.spoonofcode.feature.partner.domain.model.PartnerNotification

fun PartnerNotificationResponse.toPartnerNotification(): PartnerNotification {
    return PartnerNotification(
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