package com.spoonofcode.feature.product.data.mappers

import com.spoonofcode.feature.product.data.remote.model.ProductSeriesResponse
import com.spoonofcode.feature.product.domain.model.ProductSeries

fun ProductSeriesResponse.toProductSeries(): ProductSeries {
    return ProductSeries(
        askContactMessage = askContactMessage,
        banner = banner,
        bannerIsOn = bannerIsOn,
        blockchain = blockchain,
        contactAddress = contactAddress,
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