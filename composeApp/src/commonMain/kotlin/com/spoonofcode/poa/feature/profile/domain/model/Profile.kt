package com.spoonofcode.poa.feature.profile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val numberOfProductsOwnedByUser: Long,
    val customLink: String,
)