package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RedirectProductUpdateRequest(
    @SerialName("productType")
    val productType: String = "redirect",
    @SerialName("redirect_link")
    val redirectLink: String? = null,
)
