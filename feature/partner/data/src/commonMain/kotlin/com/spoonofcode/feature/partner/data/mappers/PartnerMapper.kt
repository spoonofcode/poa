package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.PartnerResponse
import com.spoonofcode.feature.partner.domain.model.Partner

fun PartnerResponse.toPartner(): Partner {
    return Partner(
        id = id,
        name = name,
        partnerCategory = partnerCategory,
        email = email,
        description = description,
        imageLink = imageLink,
        websiteLink = websiteLink,
        instagramLink = instagramLink,
        facebookLink = facebookLink,
        youtubeLink = youtubeLink,
        xLink = xLink,
    )
}