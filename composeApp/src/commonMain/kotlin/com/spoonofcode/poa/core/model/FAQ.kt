package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class FAQ(
    val question: String,
    val answer: String,
)

@Serializable
data class FAQRequest(
    val question: String,
    val answer: String,
)