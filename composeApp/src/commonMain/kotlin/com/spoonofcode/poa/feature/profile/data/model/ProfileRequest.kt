package com.spoonofcode.poa.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val userId: String,
    val customLink: String,
)