package com.spoonofcode.poa.core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NotificationRequest(
    val title: String,
    val text: String,
    val link: String? = null,
    val expirationDate: LocalDateTime? = null,
)

@Serializable
data class Notification(
    val id: Int,
    val title: String,
    val text: String,
    val link: String? = null,
    val expirationDate: LocalDateTime? = null,
)