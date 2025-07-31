package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val name: String,
    val description: String,
    val collectionName: String,
    val websiteLink: String? = null,
    val customLink: String? = null,
    val ownerUserId: Int,
)

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val tagId: String,
    val collectionName: String,
    val websiteLink: String? = null,
    val customLink: String? = null,
    val ownerUser: User,
)