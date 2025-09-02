package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val seriesId: String,
    val collectionName: String,
    val imageLink: String,
    val videoLink: String? =null,
    val tagId: String? = null,
    val websiteLink: String? = null,
    val customLink: String? = null,
    val ownerUser: User? = null,
)

@Serializable
data class ProductRequest(
    val name: String,
    val description: String,
    val seriesId: String,
    val collectionName: String,
    val imageLink: String,
    val videoLink: String? =null,
    val tagId: String? = null,
    val websiteLink: String? = null,
    val customLink: String? = null,
    val ownerUserId: Int? = null,
)