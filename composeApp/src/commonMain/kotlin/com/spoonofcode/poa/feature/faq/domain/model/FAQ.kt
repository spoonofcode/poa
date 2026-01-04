package com.spoonofcode.poa.feature.faq.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FAQ(
    val question: String,
    val answer: String,
)