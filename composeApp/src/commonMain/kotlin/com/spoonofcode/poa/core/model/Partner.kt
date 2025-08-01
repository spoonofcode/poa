package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerRequest(
    val name: String,
    val description: String,
    val websiteLink: String? = null,
    val instagramLink: String? = null,
    val facebookLink: String? = null,
    val youtubeLink: String? = null,
    val xLink: String? = null,
    val userId: Int,
)

@Serializable
data class Partner(
    val id: Int,
    val name: String,
    val description: String,
    val websiteLink: String? = null,
    val instagramLink: String? = null,
    val facebookLink: String? = null,
    val youtubeLink: String? = null,
    val xLink: String? = null,
)