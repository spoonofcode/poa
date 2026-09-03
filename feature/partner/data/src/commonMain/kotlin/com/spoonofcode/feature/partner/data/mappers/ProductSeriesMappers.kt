package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.ProductSeriesResponse
import com.spoonofcode.feature.partner.domain.model.ProductSeries

fun ProductSeriesResponse.toProductSeries(): ProductSeries {
    return ProductSeries(
        askContactMessage = askContactMessage,
        banner = banner,
        bannerIsOn = bannerIsOn,
        blockchain = blockchain,
        contactAddress = contractAddress,
        description = description,
        enabled = enabled,
        facebook = facebook,
        fullName = fullName,
        instagram = instagram,
        message = message,
        name = name,
        owner = owner,
        ownerEmail = ownerEmail,
        partnerLink = partnerLink,
        partnerLinkText = partnerLinkText,
        tiktok = tiktok,
        x = x,
        youtube = youtube,
    )
}