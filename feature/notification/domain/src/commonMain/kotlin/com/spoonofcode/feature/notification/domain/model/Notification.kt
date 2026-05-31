package com.spoonofcode.feature.notification.domain.model

import kotlinx.datetime.LocalDateTime

data class Notification(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    val deliveryDateTime: LocalDateTime,
    val createdDataTime: LocalDateTime,
    val modificationDateTime: LocalDateTime,
    val expirationDateTime: LocalDateTime,
    val state: String,
    val topics: List<String>,
    val isRead: Boolean = false,
    val ownerId: String,
)
