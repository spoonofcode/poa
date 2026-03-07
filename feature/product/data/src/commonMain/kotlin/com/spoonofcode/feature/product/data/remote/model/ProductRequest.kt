package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val customLink: String? = null,
    val description: String? = null,
    val imageLink: String? = null,
    val name: String? = null,
    val ownerEmail: String? = null,
    val partnerId: String? = null,
    val seriesId: String? = null,
    val seriesName: String? = null,
    val videoLink: String? =null,
)