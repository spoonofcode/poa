package com.spoonofcode.poa.feature.partner.data.mappers

import com.spoonofcode.poa.feature.partner.data.model.PartnerResponse
import com.spoonofcode.poa.feature.partner.domain.model.Partner

fun PartnerResponse.toPartner(): Partner {
    return Partner(
        id = id,
        name = name,
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