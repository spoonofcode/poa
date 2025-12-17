package com.spoonofcode.poa.feature.faq.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FAQResponse(
    val question: String,
    val answer: String,
)