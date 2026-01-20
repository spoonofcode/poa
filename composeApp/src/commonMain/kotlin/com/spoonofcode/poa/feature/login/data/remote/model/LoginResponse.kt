package com.spoonofcode.poa.feature.login.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val jwtAccessToken: String,
    @SerialName("refresh_token")
    val jwtRefreshToken: String,
)