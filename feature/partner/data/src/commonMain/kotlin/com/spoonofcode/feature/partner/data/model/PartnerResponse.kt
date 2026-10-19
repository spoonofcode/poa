package com.spoonofcode.feature.partner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PartnerResponse(
    val id: String,
    val name: String,
    @SerialName("public_email")
    val email: String? = null,
    val description: String? = null,
    val imageLink: String? = null,
    val websiteLink: String? = null,
    val instagramLink: String? = null,
    val facebookLink: String? = null,
    val youtubeLink: String? = null,
    val tiktokLink: String? = null,
    val xLink: String? = null,
    val partnerCategory: String,
)