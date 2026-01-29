package com.spoonofcode.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val customLink: String,
)