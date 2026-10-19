package com.spoonofcode.feature.partner.data.model

import com.spoonofcode.core.data.serialization.RFC1123InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
internal data class PartnerNotificationRequest(
    val title: String,
    val text: String,
    val link: String? = null,
    val topics: List<String>,
    @SerialName("delivery_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val deliveryDateTime: Instant,
    @SerialName("expiration_datetime")
    @Serializable(with = RFC1123InstantSerializer::class)
    val expirationDateTime: Instant,
    @SerialName("owner_id")
    val ownerId: String,
)
