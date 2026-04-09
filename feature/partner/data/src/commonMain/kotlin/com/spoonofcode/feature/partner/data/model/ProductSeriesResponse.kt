package com.spoonofcode.feature.partner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSeriesResponse(
    @SerialName("ask_contact_message")
    val askContactMessage: String,
    val banner: String,
    @SerialName("banner_is_on")
    val bannerIsOn: Boolean,
    val blockchain: String,
    @SerialName("contract_address")
    val contactAddress: String,
    val description: String,
    val enabled: Boolean,
    val facebook: String,
    @SerialName("full_name")
    val fullName: String,
    val instagram: String,
    val message: String,
    val name: String,
    val owner: String,
    @SerialName("owner_email")
    val ownerEmail: String,
    @SerialName("partner_link")
    val partnerLink: String,
    @SerialName("partner_link_text")
    val partnerLinkText: String,
    val tiktok: String,
    val x: String,
    val youtube: String,
)