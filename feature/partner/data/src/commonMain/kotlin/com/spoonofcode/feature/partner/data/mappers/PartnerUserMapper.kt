package com.spoonofcode.feature.partner.data.mappers

import com.spoonofcode.feature.partner.data.model.PartnerUserResponse
import com.spoonofcode.poa.feature.partner.domain.model.PartnerUser

fun PartnerUserResponse.toPartnerUser(): PartnerUser {
    return PartnerUser(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
    )
}