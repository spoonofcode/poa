package com.spoonofcode.feature.notification.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val isPersonalizedPartnerNotificationsEnabled: Boolean = true,
    val isProductSeriesNotificationsEnabled: Boolean = true,
)