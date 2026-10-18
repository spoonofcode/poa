package com.spoonofcode.feature.product.domain.model

import kotlinx.datetime.LocalDateTime

data class Product(
    val id: String,
    val productType: String,
    val ownerEmail: String? = null,
    val partnerId: String? = null,
    val name: String? = null,
    val description: String? = null,
    val seriesId: String? = null,
    val seriesName: String? = null,
    val createdAt: LocalDateTime,
    val imageLink: String? = null,
    val videoLink: String? = null,
    val customLink: String? = null,
)