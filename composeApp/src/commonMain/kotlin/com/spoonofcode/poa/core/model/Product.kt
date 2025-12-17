package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val ownerUserId: String? = null,
    val partnerId: String,
    val name: String,
    val description: String,
    val seriesId: String,
    val collectionName: String,
    val imageLink: String,
    val videoLink: String? =null,
    val tagId: String? = null,
    val websiteLink: String? = null,
    val customLink: String? = null,
)