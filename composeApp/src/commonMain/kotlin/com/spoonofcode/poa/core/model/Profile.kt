package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val numberOfProductsOwnedByUser: Long,
    val customLink: String,
)

@Serializable
data class ProfileRequest(
    val userId: String,
    val customLink: String,
)