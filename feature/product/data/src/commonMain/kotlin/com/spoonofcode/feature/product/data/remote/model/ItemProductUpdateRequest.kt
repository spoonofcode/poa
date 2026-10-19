package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemProductUpdateRequest(
    @SerialName("productType")
    val productType: String = "item",
    @SerialName("custom_link")
    val customLink: String? = null,
)
