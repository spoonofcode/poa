package com.spoonofcode.feature.profile.data.model

import com.spoonofcode.core.data.serialization.RFC1123InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
internal data class ProfileResponse(
    val id: String,
    val email: String,
    val roles: List<String>,
    @SerialName("partner_expires_at")
    @Serializable(with = RFC1123InstantSerializer::class)
    val partnerExpiresAt: Instant? = null,
    @SerialName("partner_status")
    val partnerStatus: String? = null,
)