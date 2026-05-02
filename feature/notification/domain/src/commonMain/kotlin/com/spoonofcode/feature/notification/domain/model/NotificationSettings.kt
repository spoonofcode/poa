package com.spoonofcode.feature.notification.domain.model

data class NotificationSettings(
    val isNotificationsPersonalizedEnabled: Boolean = false,
    val isNotificationsFromPartnersEnabled: Boolean = false,
    val isNotificationsMyProductSeriesEnabled: Boolean = false,
)