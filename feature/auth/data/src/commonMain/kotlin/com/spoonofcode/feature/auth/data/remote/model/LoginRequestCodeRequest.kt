package com.spoonofcode.feature.auth.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestCodeRequest(
    val email: String,
    val platform: String,
    @SerialName("recaptcha_token")
    val recaptchaToken: String,
)
