package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Partner(
    val id: String,
    val userId: String,
    val name: String,
    val email: String,
    val description: String,
    val imageLink: String,
    val websiteLink: String? = null,
    val instagramLink: String? = null,
    val facebookLink: String? = null,
    val youtubeLink: String? = null,
    val xLink: String? = null,
)