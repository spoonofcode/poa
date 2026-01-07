package com.spoonofcode.poa.feature.login.data.remote.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginGoogleRequest(
    val googleUserToken: String
)