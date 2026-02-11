package com.spoonofcode.feature.notification.data.remote.model

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
    val createdDataTime: LocalDateTime,
    @SerialName("modification_time")
    val modificationDateTime: LocalDateTime,
    @SerialName("expiration_datetime")
    val expirationDateTime: LocalDateTime? = null,
    val state: String,
    val topic: String,
)