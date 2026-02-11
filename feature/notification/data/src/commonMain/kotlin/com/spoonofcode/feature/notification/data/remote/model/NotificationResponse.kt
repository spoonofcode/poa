package com.spoonofcode.feature.notification.data.remote.model

import com.spoonofcode.core.data.serialization.LocalDateTimeRFC1123Serializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationResponse(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    @SerialName("created_datetime")
    @Serializable(with = LocalDateTimeRFC1123Serializer::class)
    val createdDataTime: LocalDateTime,
    @SerialName("modification_time")
    @Serializable(with = LocalDateTimeRFC1123Serializer::class)
    val modificationDateTime: LocalDateTime,
    @SerialName("expiration_datetime")
    @Serializable(with = LocalDateTimeRFC1123Serializer::class)
    val expirationDateTime: LocalDateTime? = null,
    val state: String,
    val topic: String,
)
