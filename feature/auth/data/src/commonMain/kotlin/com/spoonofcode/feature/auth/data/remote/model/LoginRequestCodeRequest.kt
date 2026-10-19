package com.spoonofcode.feature.auth.data.remote.model

import com.spoonofcode.core.data.recaptchaName
import com.spoonofcode.core.data.getPlatform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequestCodeRequest(
    val email: String,
    val platform: String = getPlatform().platformType.recaptchaName,
    @SerialName("recaptcha_token")
    val recaptchaToken: String,
)