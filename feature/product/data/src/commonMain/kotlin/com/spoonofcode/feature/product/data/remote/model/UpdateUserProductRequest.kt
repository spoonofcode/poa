package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserProductRequest(
    @SerialName("custom_link")
    val customLink: String? = null,
    @SerialName("redirect_link")
    val redirectLink: String? = null
)