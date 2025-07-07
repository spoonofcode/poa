package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageFCM(
    val token: String? = null,
    val topics: List<String>? = null,
    val notification: NotificationFCM,
)

@Serializable
data class NotificationFCM(
    val title: String,
    val body: String,
)