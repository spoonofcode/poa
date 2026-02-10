package com.spoonofcode.feature.notification.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val notificationFromAllPartnersEnable: Boolean = true,
    val notificationFromMyPartnersEnable: Boolean = true,
)