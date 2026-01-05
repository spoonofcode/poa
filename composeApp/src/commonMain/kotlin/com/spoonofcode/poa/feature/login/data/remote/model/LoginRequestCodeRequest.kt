package com.spoonofcode.poa.feature.login.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestCodeRequest(
    val email: String,
    val platform: String = "web",
    @SerialName("recaptcha_token")
    val recaptchaToken: String,
)