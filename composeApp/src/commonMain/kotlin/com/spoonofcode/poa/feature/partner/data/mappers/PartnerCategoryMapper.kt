package com.spoonofcode.poa.feature.partner.data.mappers

import com.spoonofcode.poa.feature.partner.data.model.PartnerCategoryResponse
import com.spoonofcode.poa.feature.partner.domain.model.PartnerCategory

fun PartnerCategoryResponse.toPartnerCategory(): PartnerCategory {
    return PartnerCategory(
        id = id,
        name = name,
    )
}