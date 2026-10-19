package com.spoonofcode.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RefreshRequest(
    @SerialName("refresh_token")
    val refreshToken: String
)