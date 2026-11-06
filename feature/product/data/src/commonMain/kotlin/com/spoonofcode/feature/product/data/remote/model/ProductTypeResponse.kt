package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class ProductTypeResponse {
    @SerialName("item")
    ITEM,

    @SerialName("redirect")
    REDIRECT,
}
