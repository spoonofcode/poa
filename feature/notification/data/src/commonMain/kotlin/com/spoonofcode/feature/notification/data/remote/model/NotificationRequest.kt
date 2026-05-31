package com.spoonofcode.feature.notification.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class NotificationRequest(
    val title: String,
    val text: String,
    val link: String? = null,
    val topics: List<String>,
    @SerialName("delivery_datetime")
    val deliveryDateTime: Instant,
    @SerialName("expiration_datetime")
    val expirationDateTime: Instant,
    @SerialName("owner_id")
    val ownerId: String,
)