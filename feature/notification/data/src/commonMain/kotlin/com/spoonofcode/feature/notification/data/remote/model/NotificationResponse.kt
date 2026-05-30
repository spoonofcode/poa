package com.spoonofcode.feature.notification.data.remote.model

import com.spoonofcode.core.data.serialization.RFC1123InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class NotificationResponse(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    @SerialName("delivery_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val deliveryDateTime: Instant,
    @SerialName("created_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val createdDataTime: Instant,
    @SerialName("modification_time")
    @Serializable(with = RFC1123InstantSerializer::class)
    val modificationDateTime: Instant,
    @SerialName("expiration_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val expirationDateTime: Instant,
    val state: String,
    val topics: List<String>,
    @SerialName("owner_id")
    val ownerId: String,
)