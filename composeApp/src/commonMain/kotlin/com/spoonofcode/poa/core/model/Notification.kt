package com.spoonofcode.poa.core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable



@Serializable
data class Notification(
    val id: String,
    val title: String,
    val text: String,
    val link: String? = null,
    val expirationDateTime: LocalDateTime? = null,
    val alreadyRead: Boolean = false,
)