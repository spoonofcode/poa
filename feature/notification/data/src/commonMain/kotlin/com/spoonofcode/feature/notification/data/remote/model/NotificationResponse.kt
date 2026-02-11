package com.spoonofcode.feature.notification.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class NotificationResponse(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    @SerialName("created_datetime")
    val createdDataTime: Instant,
    @SerialName("modification_time")
    val modificationDateTime: Instant,
    @SerialName("expiration_datetime")
    val expirationDateTime: Instant? = null,
    val state: String,
    val topic: String,
)
