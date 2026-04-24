package com.spoonofcode.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    @SerialName("access_token")
    val jwtAccessToken: String,
    @SerialName("refresh_token")
    val jwtRefreshToken: String,
)