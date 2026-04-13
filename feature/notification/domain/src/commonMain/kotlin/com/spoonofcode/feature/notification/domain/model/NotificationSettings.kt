package com.spoonofcode.feature.notification.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val isNotificationsPersonalizedEnabled: Boolean = true,
    val isNotificationsFromPartnersEnabled: Boolean = true,
    val isNotificationsMyProductSeriesEnabled: Boolean = true,
)