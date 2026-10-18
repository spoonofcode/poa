package com.spoonofcode.feature.product.data.remote.model

import com.spoonofcode.core.data.serialization.RFC1123InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class ProductResponse(
    val id: String,
    @SerialName("product_type")
    val productType: String,
    @SerialName("owner_email")
    val ownerEmail: String? = null,
    @SerialName("partner_id")
    val partnerId: String? = null,
    val name: String? = null,
    val description: String? = null,
    @SerialName("series_id")
    val seriesId: String? = null,
    @SerialName("series_name")
    val seriesName: String? = null,
    @SerialName("created_at")
    @Serializable(with = RFC1123InstantSerializer::class)
    val createdAt: Instant,
    @SerialName("image_link")
    val imageLink: String? = null,
    @SerialName("video_link")
    val videoLink: String? =null,
    @SerialName("custom_link")
    val customLink: String? = null,
)