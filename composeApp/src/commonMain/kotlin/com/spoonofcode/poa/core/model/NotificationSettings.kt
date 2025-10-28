package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val notificationFromAllPartnersEnable: Boolean = true,
    val notificationFromMyPartnersEnable: Boolean = true,
)