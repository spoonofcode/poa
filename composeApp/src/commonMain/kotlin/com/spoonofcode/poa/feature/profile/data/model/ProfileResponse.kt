package com.spoonofcode.poa.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val name: String,
    val numberOfProductsOwnedByUser: Long,
    val customLink: String,
)