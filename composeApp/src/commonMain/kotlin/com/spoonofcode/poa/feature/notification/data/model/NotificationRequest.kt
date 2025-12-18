package com.spoonofcode.poa.feature.notification.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NotificationRequest(
    val title: String,
    val text: String,
    val link: String? = null,
    val imageUrl: String? = null,
    val topics: List<String>,
    val expirationDateTime: LocalDateTime? = null,
    val alreadyRead: Boolean = false,
)