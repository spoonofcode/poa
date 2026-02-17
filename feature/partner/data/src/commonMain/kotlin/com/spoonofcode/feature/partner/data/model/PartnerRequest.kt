package com.spoonofcode.feature.partner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerRequest(
    val name: String,
    val partnerCategory: String,
    val email: String? = null,
    val description: String? = null,
    val imageLink: String? = null,
    val websiteLink: String? = null,
    val instagramLink: String? = null,
    val facebookLink: String? = null,
    val youtubeLink: String? = null,
    val xLink: String? = null,
)