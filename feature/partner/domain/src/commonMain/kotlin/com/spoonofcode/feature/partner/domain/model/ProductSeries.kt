package com.spoonofcode.feature.partner.domain.model

data class ProductSeries(
    val name: String,
    val fullName: String,
    val enabled: Boolean,
    val description: String,
    val ownerEmail: String,
    val owner: String,
    val redirectLink: String,
    val askContactMessage: String,
    val blockchain: String,
    val bannerIsOn: Boolean,
    val banner: String,
    val message: String,
    val partnerLink: String,
    val partnerLinkText: String,
    val x: String,
    val instagram: String,
    val youtube: String,
    val facebook: String,
    val tiktok: String,
)