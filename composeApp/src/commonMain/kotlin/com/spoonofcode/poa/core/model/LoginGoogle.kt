package com.spoonofcode.poa.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val userId: Int,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class LoginGoogleRequest(
    @SerialName("id_token")
    val googleUserToken: String
)