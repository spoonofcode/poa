package com.spoonofcode.feature.notification.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val isNotificationsPersonalizedEnabled: Boolean = false,
    val isNotificationsFromPartnersEnabled: Boolean = false,
    val isNotificationsMyProductSeriesEnabled: Boolean = false,
)