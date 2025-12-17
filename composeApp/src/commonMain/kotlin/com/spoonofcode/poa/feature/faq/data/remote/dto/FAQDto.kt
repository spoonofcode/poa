package com.spoonofcode.poa.feature.faq.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FAQDto(
    val question: String,
    val answer: String,
)