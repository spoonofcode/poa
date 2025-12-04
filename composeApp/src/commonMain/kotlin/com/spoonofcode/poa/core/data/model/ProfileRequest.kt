package com.spoonofcode.poa.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val userId: String,
    val customLink: String,
)