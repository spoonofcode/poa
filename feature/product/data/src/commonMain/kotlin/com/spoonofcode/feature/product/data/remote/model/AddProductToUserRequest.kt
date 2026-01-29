package com.spoonofcode.feature.profile.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProductToUserRequest(
    val productId: String,
)