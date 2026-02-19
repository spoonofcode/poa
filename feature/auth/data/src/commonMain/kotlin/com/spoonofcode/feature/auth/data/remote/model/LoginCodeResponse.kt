package com.spoonofcode.feature.auth.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginCodeResponse(
    @SerialName("access_token")
    val jwtAccessToken: String,
    @SerialName("refresh_token")
    val jwtRefreshToken: String,
    @SerialName("user_profile")
    val userProfile: UserProfile
) {
    @Serializable
    data class UserProfile(
        val email: String,
        val id: String,
        val roles: List<String> = emptyList()
    )
}