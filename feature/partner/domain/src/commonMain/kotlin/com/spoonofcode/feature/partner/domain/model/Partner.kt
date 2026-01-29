package com.spoonofcode.feature.partner.domain.model

data class Partner(
    val id: String,
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