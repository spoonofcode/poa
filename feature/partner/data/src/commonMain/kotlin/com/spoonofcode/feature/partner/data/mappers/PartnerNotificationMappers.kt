package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.partner.data.model.PartnerNotificationResponse
import com.spoonofcode.feature.partner.domain.model.PartnerNotification

internal fun PartnerNotificationResponse.toPartnerNotification(): PartnerNotification =
    PartnerNotification(
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