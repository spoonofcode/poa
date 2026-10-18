package com.spoonofcode.feature.partner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSeriesResponse(
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val enabled: Boolean,
    val description: String,
    @SerialName("owner_email")
    val ownerEmail: String,
    val owner: String,
    @SerialName("redirect_link")
    val redirectLink: String,
    @SerialName("ask_contact_message")
    val askContactMessage: String,
    val blockchain: String,
    @SerialName("banner_is_on")
    val bannerIsOn: Boolean,
    val banner: String,
    val message: String,
    @SerialName("partner_link")
    val partnerLink: String,
    @SerialName("partner_link_text")
    val partnerLinkText: String,
    val x: String,
    val instagram: String,
    val youtube: String,
    val facebook: String,
    val tiktok: String,
)