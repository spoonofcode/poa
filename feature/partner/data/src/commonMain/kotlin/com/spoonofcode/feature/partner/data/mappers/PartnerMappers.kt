package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.PartnerResponse
import com.spoonofcode.feature.partner.domain.model.Partner

internal fun PartnerResponse.toPartner(): Partner = Partner(
    id = id,
    name = name,
    email = email,
    description = description,
    imageLink = imageLink,
    websiteLink = websiteLink,
    instagramLink = instagramLink,
    facebookLink = facebookLink,
    youtubeLink = youtubeLink,
    tiktokLink = tiktokLink,
    xLink = xLink,
    partnerCategory = partnerCategory,
)