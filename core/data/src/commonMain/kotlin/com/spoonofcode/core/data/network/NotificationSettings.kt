package com.spoonofcode.core.data.network

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val isNotificationsPersonalizedEnabled: Boolean = false,
    val isNotificationsFromPartnersEnabled: Boolean = false,
    val isNotificationsMyProductSeriesEnabled: Boolean = false,
)