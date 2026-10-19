package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.ProductSeriesResponse
import com.spoonofcode.feature.partner.domain.model.ProductSeries

internal fun ProductSeriesResponse.toProductSeries(): ProductSeries = ProductSeries(
    name = name,
    fullName = fullName,
    enabled = enabled,
    description = description,
    ownerEmail = ownerEmail,
    owner = owner,
    redirectLink = redirectLink,
    askContactMessage = askContactMessage,
    blockchain = blockchain,
    bannerIsOn = bannerIsOn,
    banner = banner,
    message = message,
    partnerLink = partnerLink,
    partnerLinkText = partnerLinkText,
    x = x,
    instagram = instagram,
    youtube = youtube,
    facebook = facebook,
    tiktok = tiktok,
)