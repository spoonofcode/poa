package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val userId: Int,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class LoginGoogleRequest(
    //TODO #13 Switch to POA backend API
//    @SerialName("id_token")
    val googleUserToken: String
)