package com.spoonofcode.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileRequest(
    val customLink: String,
)