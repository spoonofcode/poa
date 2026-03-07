package com.spoonofcode.feature.product.domain.model

import kotlinx.datetime.LocalDateTime

data class Product(
    val id: String,
    val createdAt: LocalDateTime,
    val customLink: String? = null,
    val description: String? = null,
    val imageLink: String? = null,
    val name: String? = null,
    val ownerEmail: String? = null,
    val partnerId: String? = null,
    val seriesId: String? = null,
    val seriesName: String? = null,
    val videoLink: String? = null,
)