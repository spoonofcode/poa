package com.spoonofcode.feature.auth.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginGoogleResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("user_profile")
    val userProfile: UserProfile,
) {
    @Serializable
    data class UserProfile(
        val email: String,
        val id: String,
        val roles: List<String> = emptyList(),
    )
}
