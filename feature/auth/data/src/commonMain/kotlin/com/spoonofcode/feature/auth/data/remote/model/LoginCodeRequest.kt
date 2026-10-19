package com.spoonofcode.feature.auth.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginCodeRequest(
    val email: String,
    @SerialName("login_code")
    val loginCode: String,
)